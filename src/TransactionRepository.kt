interface TransactionRepository {

    fun addTransaction(amount: Double, category: Category): Boolean

    fun editTransactionAmount(id: Int, amount: Double): Boolean

    fun editTransactionCategory(id: Int, category: Category): Boolean

    fun deleteTransaction(id: Int): Boolean

    fun getTransactionsDetails(transactionType: TransactionType): String

    fun getAllTransactions(): List<Transaction>
}