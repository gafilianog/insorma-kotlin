package dev.gafilianog.insorma.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.gafilianog.insorma.data.local.dao.ProductDao
import dev.gafilianog.insorma.data.local.dao.TransactionDao
import dev.gafilianog.insorma.data.local.dao.UsersDao
import dev.gafilianog.insorma.data.model.Product
import dev.gafilianog.insorma.data.model.Transaction
import dev.gafilianog.insorma.data.model.User

@Database(
    entities = [Product::class, Transaction::class, User::class],
    version = 1,
    exportSchema = false
)
abstract class InsormaDatabase : RoomDatabase() {

//    abstract fun productDao(): ProductDao
//    abstract val productDao: ProductDao

//    abstract fun transactionDao(): TransactionDao
//    abstract val transactionDao: TransactionDao

//    abstract fun usersDao(): UsersDao
    abstract val usersDao: UsersDao

    companion object {
        @Volatile
        private var DB_INSTANCE: InsormaDatabase? = null

        fun getDatabase(context: Context): InsormaDatabase {
            return DB_INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InsormaDatabase::class.java,
                    "insorma-db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                DB_INSTANCE = instance
                instance
            }
        }
    }
}