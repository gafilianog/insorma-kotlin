package dev.gafilianog.insorma.data.local.repos

import dev.gafilianog.insorma.data.local.dao.TransactionDao
import dev.gafilianog.insorma.data.model.Transaction

class TransactionRepository(private val dao: TransactionDao) {

    suspend fun insertTransaction(transaction: Transaction) {
        return dao.insertTransaction(transaction)
    }
}