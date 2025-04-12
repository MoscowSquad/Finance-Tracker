interface TransactionRepository {

    fun addTransaction(amount: Double, category: Category): Boolean

    fun editTransactionAmount(id: Int, amount: Double, type: TransactionType): Boolean

    fun editTransactionCategory(id: Int, category: Category, type: TransactionType): Boolean

    fun deleteTransaction(id: Int, type: TransactionType): Boolean

    fun getTransactionsDetails(transactionType: TransactionType?): String

    fun getAllTransactions(): List<Transaction>
}