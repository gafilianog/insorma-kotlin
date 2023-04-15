package dev.gafilianog.insorma.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
)
