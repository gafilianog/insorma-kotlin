package dev.gafilianog.insorma.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.gafilianog.insorma.data.local.LoggedInUserDatastoreImpl
import dev.gafilianog.insorma.data.local.repos.UserRepository

class LoginViewModelFactory(
    private val userRepo: UserRepository,
    private val loggedInUserDataStore: LoggedInUserDatastoreImpl,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userRepo, loggedInUserDataStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}