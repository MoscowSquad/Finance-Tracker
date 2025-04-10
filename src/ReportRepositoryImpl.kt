import java.time.Month

class ReportRepositoryImpl(
    private val transactions: List<Transaction>
) : ReportRepository {
    override fun prepareMonthlySummary(month: Month): MonthlySummary {
        var totalIncomes = 0.0
        var totalExpenses = 0.0
        transactions.forEach { transaction ->
            if (transaction.date.month == month) {
                if (transaction.type == TransactionType.INCOME)
                    totalIncomes = transaction.amount
                if (transaction.type == TransactionType.EXPENSE)
                    totalExpenses = transaction.amount
            }
        }

        val balance = totalIncomes - totalExpenses
        return MonthlySummary(totalIncomes, totalExpenses, balance)
    }
}