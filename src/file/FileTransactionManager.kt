package file

import Transaction
import java.io.File

class FileTransactionManager(
    private val storageOperation: StorageOperation,
    private val storagePath: String = "data/transactions.csv",
    private var userManager: UserManager?
) : FileTransactionOperations {
    private val transactions = mutableListOf<Transaction>()

    init {
        val file = File(storagePath)
        file.parentFile?.mkdirs()
        val (loadedTransactions, loadedUserName) = StorageOperationManager.loadFromFile(storagePath)
        transactions.addAll(loadedTransactions)
        userManager = loadedUserName?.let { UserManager(

            storagePath = storagePath,
            storageOperation = storageOperation,
        ) } 
    }

    override fun addAllTransactions(transactions: List<Transaction>, userName: String?) {
        this.transactions.addAll(transactions)
        storageOperation.saveToFile(this.transactions, storagePath, userName)
    }

    override fun getAllTransactions(): List<Transaction> {
        return transactions.toList()
    }
}