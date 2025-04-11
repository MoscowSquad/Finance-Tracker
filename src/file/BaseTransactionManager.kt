package file

import Transaction
import java.io.File

open class BaseTransactionManager(protected val storagePath: String) {
    val transactions = mutableListOf<Transaction>()
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


    fun addUserName(name: String?) {
        if (userName != null) {
            println("User name already exists: $userName")
            return
        }

        val inputName = name ?: run {
            println("No user found. Please enter your name:")
            val userInput = readlnOrNull() ?: ""
            if (userInput.isBlank()) "Unknown" else userInput
        }

        userName = inputName
        StorageManager.saveToFile(transactions, storagePath, userName)
    }

    open fun addTransaction(transaction: Transaction) {
        transactions.add(transaction)
        StorageManager.saveToFile(transactions, storagePath, userName)
    }

    fun getUserName(): String? {
        return userName
    }
}