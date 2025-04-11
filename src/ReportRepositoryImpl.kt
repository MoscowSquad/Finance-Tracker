import java.time.Month

class ReportRepositoryImpl : ReportRepository {
    override fun prepareTransactionSummary(transactions: List<Transaction>, month: Month?): TransactionSummary {
        var totalIncomes = 0.0
        var totalExpenses = 0.0
        transactions.forEach { transaction ->
            if (month == null || transaction.date.month == month) {
                if (transaction.type == TransactionType.INCOME)
                    totalIncomes = transaction.amount
                if (transaction.type == TransactionType.EXPENSE)
                    totalExpenses = transaction.amount
            }
        }

        val balance = totalIncomes - totalExpenses
        return TransactionSummary(totalIncomes, totalExpenses, balance)
    }
}