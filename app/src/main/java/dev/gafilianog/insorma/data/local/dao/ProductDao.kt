package dev.gafilianog.insorma.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import dev.gafilianog.insorma.data.model.Product

interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: Product)
}