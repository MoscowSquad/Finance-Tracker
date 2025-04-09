package file
import Transaction


interface TransactionAdderFile {
    fun addTransaction(transaction: Transaction)
}

interface TransactionRetrieverFile {
    fun getSpecificTransaction(id: Int): Transaction?
    fun getAllTransaction(): List<Transaction>
    fun getMonthlyTransactions(month: Int): List<Transaction>
}
