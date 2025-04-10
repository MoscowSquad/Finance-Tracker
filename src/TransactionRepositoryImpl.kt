import java.time.LocalDateTime

class TransactionRepositoryImpl(
    private val transactions: MutableList<Transaction>
) : TransactionRepository {

    override fun addTransaction(transaction: Transaction): Boolean {
        return try {
            val newId = transactions.size + 1
            val newTransaction = transaction.copy(id = newId)
            transactions.add(newTransaction)
            println("Transaction added successfully")
            true
        } catch (e: Exception) {
            val errorMessage = "something went wrong"
            println("Error adding transaction: $errorMessage")
            false
        }
    }

    override fun editTransaction(transaction: Transaction): Boolean {
        val index = transactions.indexOfFirst { it.id == transaction.id }
        return if (index != -1) {
            transactions[index] = transaction
            println("Transaction with ID ${transaction.id} updated successfully")
            true
        } else {
            println("Error: Transaction with ID ${transaction.id} not found")
            false
        }
    }

    override fun deleteTransaction(id: Int): Boolean {
        val index = transactions.indexOfFirst { it.id == id }
        return if (index != -1) {
            transactions.removeAt(index)
            true
        } else {
            false
        }
    }
}
