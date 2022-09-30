package dev.gafilianog.insorma.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "LOGGED_IN_USER")

class LoggedInUserDatastoreImpl(val context: Context) {

    companion object {
        val EMAIL = stringPreferencesKey("EMAIL")
        val USERNAME = stringPreferencesKey("USERNAME")
        val PHONE_NUMBER = stringPreferencesKey("PHONE_NUMBER")
    }

    suspend fun setLoggedInUser(email: String, username: String, phone: String) {
        context.dataStore.edit { loggedInUser ->
            loggedInUser[EMAIL] = email
            loggedInUser[USERNAME] = username
            loggedInUser[PHONE_NUMBER] = phone
        }
    }

    suspend fun setNewUsername(newUsername: String) {
        context.dataStore.edit { loggedInUser ->
            loggedInUser[USERNAME] = newUsername
        }
    }

    suspend fun getLoggedInUserEmail(): String? {
        return context.dataStore.data.first()[EMAIL]
    }

    suspend fun getLoggedInUserUsername(): String? {
        return context.dataStore.data.first()[USERNAME]
    }

    suspend fun getLoggedInUserPhone(): String? {
        return context.dataStore.data.first()[PHONE_NUMBER]
    }

    suspend fun clearDatastore() {
        context.dataStore.edit { it.clear() }
    }
}