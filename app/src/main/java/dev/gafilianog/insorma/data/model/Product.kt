package dev.gafilianog.insorma.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = false)
    val product_name: String,
    val rating: String,
    val price: String,
    val image: String,
    val description: String,
)
