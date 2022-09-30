package dev.gafilianog.insorma.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "UserID")
    val _id: Int,
    @ColumnInfo(name = "UserEmailAddress")
    val emailAddress: String,
    @ColumnInfo(name = "UserUsername")
    var username: String,
    @ColumnInfo(name = "UserPhoneNumber")
    val phoneNumber: String,
    @ColumnInfo(name = "UserPassword")
    val password: String,
)
