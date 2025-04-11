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
        val transactionIndex = findTransactionIndexById(id)

        if (id == 0 || amount <= 0 || transactionIndex == -1)
            return false

        transactions[transactionIndex] = transactions[transactionIndex].copy(amount = amount)
        return true
    }

    override fun editTransactionCategory(id: Int, category: Category): Boolean {
        val transactionIndex = findTransactionIndexById(id)

        if (id == 0 || transactionIndex == -1)
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

    override fun getTransactionsDetail(): String {
        var str = ""
        transactions.forEach { transaction ->
            val categoryStr = when (transaction.category) {
                Category.Food -> "Food"
                Category.Salary -> "Salary"
            }
            val typeSymbol = if (transaction.type == TransactionType.INCOME) "++" else "--"
            str += "$typeSymbol${transaction.amount} ${transaction.date} :$categoryStr\n"
        }
        return str
    }
}