import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TransactionRepositoryImpl(
    private val transactions: MutableList<Transaction>,
) : TransactionRepository {

    override fun addTransaction(amount: Double, category: Category): Boolean {
        if (amount <= 0)
            return false

        if (!canAddTransaction(category))
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

    private fun canAddTransaction(category: Category): Boolean {
        if (category.type == TransactionType.INCOME)
            return true

        var balance = 0.0
        transactions.forEach { transaction ->
            if (transaction.type == TransactionType.INCOME) {
                balance += transaction.amount
            } else {
                balance -= transaction.amount
            }
        }
        return balance >= 0
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

    private fun findTransactionIndexById(id: Int): Int {
        if (id == 0)
            return -1
        return transactions.indexOfFirst { it.id == id }
    }

    override fun getTransactionsDetails(transactionType: TransactionType?): String {
        val filteredTransactions =
            if (transactionType == null) transactions else transactions.filter { it.type == transactionType }

        if (filteredTransactions.isEmpty()) return "No ${transactionType?.name?.lowercase()?:"\b"} transactions found."

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")

        val result = buildString {
            appendLine("ID   | Type   | Amount   | Date & Time       | Category")
            appendLine("---------------------------------------------------------------")
            filteredTransactions.forEach { transaction ->
                val typeSymbol = if (transaction.type == TransactionType.INCOME) "Income" else "Expense"
                val formattedDateTime = transaction.date.format(formatter)
                appendLine(
                    "%-4s | %-6s | %-8.2f | %-17s | %s".format(
                        transaction.id,
                        typeSymbol,
                        transaction.amount,
                        formattedDateTime,
                        transaction.category.title
                    )
                )
            }
        }

        return result
    }

    override fun getAllTransactions(): List<Transaction> = transactions
}