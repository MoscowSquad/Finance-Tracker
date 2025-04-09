class ReportRepositoryImpl(
    private val transactions: List<Transaction>
) {
    fun prepareMonthlySummary(): MonthlySummary {
        val monthlySummary = MonthlySummary(0.0, 0.0, 0.0)
        transactions.forEach { transaction ->
            monthlySummary.totalIncomes = transaction.amount
            monthlySummary.totalExpenses = transaction.amount
        }
        monthlySummary.balance = monthlySummary.totalIncomes - monthlySummary.totalExpenses
        return monthlySummary
    }
}