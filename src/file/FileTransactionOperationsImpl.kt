package file

import Transaction
import java.io.File

class FileTransactionManager(
    private val storageOperation: StorageOperation,
    private val storagePath: String = "data/transactions.csv",
) : FileTransactionOperations {

    override fun addAllTransactions(transactions: List<Transaction>, userName: String?) {
        storageOperation.saveToFile(transactions, storagePath, userName)
    }

    override fun getAllTransactions(): List<Transaction> {
        val file = File(storagePath)
        file.parentFile?.mkdirs()
        return StorageOperationManager.loadFromFile(storagePath).first
    }
}