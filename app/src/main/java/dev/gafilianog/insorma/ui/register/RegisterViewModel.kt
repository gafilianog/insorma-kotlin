package dev.gafilianog.insorma.ui.register

import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gafilianog.insorma.R
import dev.gafilianog.insorma.data.local.repos.UserRepository
import dev.gafilianog.insorma.data.model.User
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepo: UserRepository) : ViewModel(), Observable {

    @Bindable
    val inputEmailRegister = MutableLiveData<String?>()

    @Bindable
    val inputUsernameRegister = MutableLiveData<String?>()

    @Bindable
    val inputPhoneRegister = MutableLiveData<String?>()

    @Bindable
    val inputPasswordRegister = MutableLiveData<String?>()

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean>
        get() = _navigateToLogin

    private val _registerErrStatus = MutableLiveData<Boolean>()
    val registerErrStatus: LiveData<Boolean>
        get() = _registerErrStatus

    private var _registerErrMsg: Int? = null
    val registerErrMsg: Int
        get() = _registerErrMsg!!

    fun register() {
        if (inputEmailRegister.value == null || inputUsernameRegister.value == null || inputPhoneRegister.value == null || inputPasswordRegister.value == null) {
            _registerErrMsg = R.string.msg_err_empty_field
            _registerErrStatus.value = true
        } else {
            viewModelScope.launch {
                if (userRepo.getUserByEmail(inputEmailRegister.value!!) != null) {
                    _registerErrMsg = R.string.msg_err_email_not_unique
                    _registerErrStatus.value = true
                } else if (userRepo.getUserByUsername(inputUsernameRegister.value!!) != null) {
                    _registerErrMsg = R.string.msg_err_username_not_unique
                    _registerErrStatus.value = true
                } else {
                    if (validate()) {
                        insertUser(
                            User(
                                0,
                                inputEmailRegister.value!!,
                                inputUsernameRegister.value!!,
                                inputPhoneRegister.value!!,
                                inputPasswordRegister.value!!
                            )
                        )

                        inputEmailRegister.value = null
                        inputUsernameRegister.value = null
                        inputPhoneRegister.value = null
                        inputPasswordRegister.value = null
                        _navigateToLogin.value = true
                    }
                }
            }
        }
    }

    private fun validate(): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(inputEmailRegister.value!!).matches()) {
            _registerErrMsg = R.string.msg_err_email_not_valid
            _registerErrStatus.value = true
            return false
        }

        if (inputUsernameRegister.value!!.length < 3 || inputUsernameRegister.value!!.length > 20) {
            _registerErrMsg = R.string.msg_err_username_length
            _registerErrStatus.value = true
            return false
        }

        if (!inputPasswordRegister.value!!.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$".toRegex())) {
            _registerErrMsg = R.string.msg_err_password_not_valid
            _registerErrStatus.value = true
            return false
        }

        return true
    }

    private fun insertUser(user: User) = viewModelScope.launch {
        userRepo.insertUser(user)
    }

    fun showErrDone() {
        _registerErrStatus.value = false
    }

    fun moveToLogin() {
        _navigateToLogin.value = true
    }

    fun moveToLoginDone() {
        _navigateToLogin.value = false
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}