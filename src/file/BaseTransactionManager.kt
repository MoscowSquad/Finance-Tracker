package file

import Transaction
import java.io.File

open class BaseTransactionManager(protected val storagePath: String) {
    protected val transactions = mutableListOf<Transaction>()
    private var userName: String? = null

    init {
        val file = File(storagePath)
        file.parentFile?.mkdirs()
        val (loadedTransactions, loadedUserName) = StorageManager.loadFromFile(storagePath)
        transactions.addAll(loadedTransactions)
        userName = loadedUserName
    }

    fun hasUserName(): Boolean {
        return userName != null
    }


    fun addUserName(name:String) {
        if (name != null) {
            println("User name already exists: $name")
            return
        }
        println("No user found. Please enter your name:")
        val inputName = readln() ?: ""
        userName = if (inputName.isBlank()) "Unknown" else inputName
        StorageManager.saveToFile(transactions, storagePath, userName)
        println("Hello, $userName! Your name has been saved.")
    }

    open fun addTransaction(transaction: Transaction) {
        transactions.add(transaction)
        StorageManager.saveToFile(transactions, storagePath, userName)
    }

    fun getUserName(): String? {
        return userName
    }
}