package file
import Transaction


interface FileTransactionOperations {
    fun addAllTransactions(transactions: List<Transaction>, userName: String?)
    fun getAllTransactions(): List<Transaction>
}

