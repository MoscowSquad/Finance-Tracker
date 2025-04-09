package file

import Transaction
import java.io.File

class TransactionAdderFileImpl(storagePath: String = "data/transactions.csv") : BaseTransactionManager(storagePath), TransactionAdderFile {


    override fun addTransaction(transaction: Transaction) {
        transactions.add(transaction)
        StorageManager.saveToFile(transactions, storagePath)
    }

}



