package dev.gafilianog.insorma.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["UserID"],
            childColumns = ["userId"],
        ),
        ForeignKey(
            entity = Product::class,
            parentColumns = ["product_name"],
            childColumns = ["productId"],
        ),
    ]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "TransactionID")
    var _id: Int,
    val userId: Int,
    val productId: String,
//    val transactionDate: Date,
    // TODO: How to store Date in Room
    val quantity: Int,
)
