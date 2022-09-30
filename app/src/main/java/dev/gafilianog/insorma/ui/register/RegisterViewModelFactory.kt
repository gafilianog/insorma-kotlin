package dev.gafilianog.insorma.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.gafilianog.insorma.data.local.repos.UserRepository
import java.lang.IllegalArgumentException

class RegisterViewModelFactory(private val userRepo: UserRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(userRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}