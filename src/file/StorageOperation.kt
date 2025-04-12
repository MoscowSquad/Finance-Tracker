package file

import Transaction

interface StorageOperation {
    fun saveToFile(transactions: List<Transaction>, storagePath: String, userName: String?)
    fun loadTransactionFromFile(storagePath: String): List<Transaction>
    fun loadNameFromFile(storagePath: String):  String?

}