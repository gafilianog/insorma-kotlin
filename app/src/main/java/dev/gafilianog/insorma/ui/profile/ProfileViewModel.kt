package dev.gafilianog.insorma.ui.profile

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gafilianog.insorma.R
import dev.gafilianog.insorma.data.local.LoggedInUserDatastoreImpl
import dev.gafilianog.insorma.data.local.repos.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepo: UserRepository,
    private val loggedInUserDatastore: LoggedInUserDatastoreImpl,
) : ViewModel(), Observable {

    var userEmail: String? = null
    var userPhone: String? = null

    @Bindable
    val inputNewUsername = MutableLiveData<String?>()

    private val _userUsername = MutableLiveData<String>()
    val userUsername: LiveData<String> = _userUsername

    private val _editUsernameStatus = MutableLiveData<Boolean>()
    val editUsernameStatus: LiveData<Boolean>
        get() = _editUsernameStatus

    private val _editErrStatus = MutableLiveData<Boolean>()
    val editErrStatus: LiveData<Boolean>
        get() = _editErrStatus

    private var _editErrMsg: Int? = null
    val editErrMsg: Int
        get() = _editErrMsg!!

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean>
        get() = _navigateToLogin

    init {
        viewModelScope.launch {
            userEmail = loggedInUserDatastore.getLoggedInUserEmail()
            userPhone = loggedInUserDatastore.getLoggedInUserPhone()
            _userUsername.value = loggedInUserDatastore.getLoggedInUserUsername()
        }
    }

    fun editUsername() {
        _editUsernameStatus.value = true
    }

    fun saveUsername() {
        val newUsername: String = inputNewUsername.value!!

        viewModelScope.launch {
            // ugh rasanya ga aman kalau return object include password, apalagi dah ada datastore. for the sake of prompt la
            val user = userRepo.getUserByUsername(userUsername.value!!)

            if (userRepo.getUserByUsername(newUsername) != null) {
                _editErrMsg = R.string.msg_err_username_not_unique
                _editErrStatus.value = true
            } else if (newUsername.length < 3 || newUsername.length > 20) {
                _editErrMsg = R.string.msg_err_username_length
                _editErrStatus.value = true
            } else {
                if (user != null) {
                    userRepo.updateUsername(newUsername, user._id)
                    _userUsername.value = newUsername
                    loggedInUserDatastore.setNewUsername(newUsername)
                    _editUsernameStatus.value = false
                    inputNewUsername.value = null
                }
            }
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            userRepo.deleteUser(_userUsername.value!!)
        }
        _navigateToLogin.value = true
    }

    fun logout() {
        viewModelScope.launch {
            loggedInUserDatastore.clearDatastore()
        }
        _navigateToLogin.value = true
    }

    fun showErrDone() {
        _editErrStatus.value = false
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}