interface TransactionRepository {

    fun addTransaction(transaction: Transaction):Transaction

    fun editTransaction(transaction: Transaction):Transaction?
}