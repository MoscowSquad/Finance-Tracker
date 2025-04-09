package file

import Transaction

interface Storage {
    fun saveToFile(transactions: List<Transaction>, storagePath: String, userName: String?)
    fun loadFromFile(storagePath: String): Pair<List<Transaction>, String?>
}