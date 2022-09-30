package dev.gafilianog.insorma.data.local.dao

import androidx.room.*
import dev.gafilianog.insorma.data.model.User

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("UPDATE Users SET UserUsername = :newUsername WHERE UserID = :userId")
    suspend fun updateUsername(newUsername: String, userId: Int)

    @Query("SELECT * FROM Users WHERE UserEmailAddress = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM Users WHERE UserUsername = :username")
    suspend fun getUserByUsername(username: String): User?

    @Query("DELETE FROM Users WHERE UserUsername = :username")
    suspend fun deleteUser(username: String)
}