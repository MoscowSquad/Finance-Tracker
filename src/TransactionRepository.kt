interface TransactionRepository {

    fun addTransaction(transaction: Transaction): Boolean

    fun editTransaction(transaction: Transaction): Boolean

    fun deleteTransaction(id: Int): Boolean
}