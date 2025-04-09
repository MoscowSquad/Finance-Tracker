package file

import Transaction
import java.io.File

open class BaseTransactionManager(protected val storagePath: String) {
    protected val transactions = mutableListOf<Transaction>()

    init {
        val file = File(storagePath)
        file.parentFile?.mkdirs()
        val loadedTransactions = StorageManager.loadFromFile(storagePath)
        transactions.addAll(loadedTransactions)
    }
}
