import java.time.LocalDateTime

class TransactionRepositoryImpl(
    private val transactions: MutableList<Transaction>
): TransactionRepository {

    override fun addTransaction(transaction: Transaction): Transaction {
        val newId = transactions.size + 1
        val newTransaction = transaction.copy(id = newId)
        transactions.add(newTransaction)
        return newTransaction
    }

    override fun editTransaction(transaction: Transaction): Transaction? {
       val index = transactions.indexOfFirst{it.id == transaction.id}
       if(index != -1){
           transactions[index]= transaction
           return transaction
       }
        return null
    }

}
