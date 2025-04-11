import java.time.LocalDateTime

class TransactionRepositoryImpl(
    private val transactions: MutableList<Transaction>
) : TransactionRepository {

    override fun addTransaction(amount: Double, category: Category): Boolean {
        if (amount <= 0)
            return false

        val newId = if (transactions.isEmpty()) 1
        else transactions.maxOf { it.id } + 1

        val transaction = Transaction(
            id = newId, amount = amount, type = category.type,
            category = category, date = LocalDateTime.now()
        )

        transactions.add(transaction)
        return true
    }

    override fun editTransactionAmount(id: Int, amount: Double): Boolean {
        if (id == 0)
            return false

        if (amount <= 0)
            return false

        val transactionIndex = findTransactionIndexById(id)
        if (transactionIndex == -1)
            return false

        transactions[transactionIndex] = transactions[transactionIndex].copy(amount = amount)
        return true
    }

    override fun editTransactionCategory(id: Int, category: Category): Boolean {
        if (id == 0)
            return false

        val transactionIndex = findTransactionIndexById(id)
        if (transactionIndex == -1)
            return false

        transactions[transactionIndex] = transactions[transactionIndex].copy(category = category)
        return true
    }

    override fun deleteTransaction(id: Int): Boolean {
        val transactionIndex = findTransactionIndexById(id)
        if (transactionIndex == -1)
            return false

        transactions.removeAt(transactionIndex)
        return true
    }

    override fun findTransactionIndexById(id: Int): Int {
        if (id == 0)
            return -1
        return transactions.indexOfFirst { it.id == id }
    }
}
