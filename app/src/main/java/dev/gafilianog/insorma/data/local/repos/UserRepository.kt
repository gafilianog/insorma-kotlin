package dev.gafilianog.insorma.data.local.repos

import dev.gafilianog.insorma.data.local.dao.UsersDao
import dev.gafilianog.insorma.data.model.User

class UserRepository(private val dao: UsersDao) {

    suspend fun insertUser(user: User) {
        return dao.insertUser(user)
    }

    suspend fun updateUsername(newUsername: String, userId: Int) {
        return dao.updateUsername(newUsername, userId)
    }

    suspend fun getUserByEmail(email: String): User? {
        return dao.getUserByEmail(email)
    }

    suspend fun getUserByUsername(username: String): User? {
        return dao.getUserByUsername(username)
    }

    suspend fun deleteUser(username: String) {
        return dao.deleteUser(username)
    }
}