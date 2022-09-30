package dev.gafilianog.insorma.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.gafilianog.insorma.data.local.LoggedInUserDatastoreImpl
import dev.gafilianog.insorma.data.local.repos.UserRepository

class ProfileViewModelFactory(
    private val userRepo: UserRepository,
    private val loggedInUserDataStore: LoggedInUserDatastoreImpl,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(userRepo, loggedInUserDataStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}