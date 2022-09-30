package dev.gafilianog.insorma.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "ProductName")
    val _name: String,
    @ColumnInfo(name = "ProductRating")
    val rating: Double,
    @ColumnInfo(name = "ProductPrice")
    val price: Int,
    @ColumnInfo(name = "ProductImage")
    val imageUrl: String,
    @ColumnInfo(name = "ProductDescription")
    val description: String,
)
