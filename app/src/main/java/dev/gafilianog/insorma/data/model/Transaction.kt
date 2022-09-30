package dev.gafilianog.insorma.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["UserID"],
            childColumns = ["userId"],
        ),
        ForeignKey(
            entity = Product::class,
            parentColumns = ["ProductName"],
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
    val quantity: Int,
)
