package dev.gafilianog.insorma.ui.login

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gafilianog.insorma.R
import dev.gafilianog.insorma.data.local.LoggedInUserDatastoreImpl
import dev.gafilianog.insorma.data.local.repos.UserRepository
import dev.gafilianog.insorma.data.remote.ApiClient
import dev.gafilianog.insorma.data.remote.LoginRequest
import dev.gafilianog.insorma.data.remote.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Call

class LoginViewModel(
    private val userRepo: UserRepository,
    private val loggedInUserDatastore: LoggedInUserDatastoreImpl,
) : ViewModel(), Observable {

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val inputPassword = MutableLiveData<String?>()

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean>
        get() = _navigateToHome

    private val _navigateToRegister = MutableLiveData<Boolean>()
    val navigateToRegister: LiveData<Boolean>
        get() = _navigateToRegister

    private val _loginErrStatus = MutableLiveData<Boolean>()
    val loginErrStatus: LiveData<Boolean>
        get() = _loginErrStatus

    private var _loginErrMsg: Int? = null
    val loginErrMsg: Int
        get() = _loginErrMsg!!

    fun login() {
        if (inputEmail.value == null || inputPassword.value == null) {
            _loginErrMsg = R.string.msg_err_empty_field
            _loginErrStatus.value = true
        } else {
            viewModelScope.launch {
                val message = ApiClient.api.login(
                    LoginRequest(
                        inputEmail.value!!,
                        inputPassword.value!!
                    )
                ).message

                if (message == "Access granted") {
                    val user = ApiClient.api.getHome()

                    loggedInUserDatastore.setLoggedInUser(
                        user.email,
                        user.name,
                        user.password,
                    )
                    inputEmail.value = null
                    inputPassword.value = null
                    _navigateToHome.value = true
                } else {
                    _loginErrMsg = R.string.msg_err_email_not_found
                    _loginErrStatus.value = true
                }
            }
        }
    }

    fun showErrDone() {
        _loginErrStatus.value = false
    }

    fun moveToRegister() {
        _navigateToRegister.value = true
    }

    fun moveToRegisterDone() {
        _navigateToRegister.value = false
    }

    fun moveToHomeDone() {
        _navigateToHome.value = false
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}