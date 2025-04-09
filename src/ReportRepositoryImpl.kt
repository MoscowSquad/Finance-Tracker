class ReportRepositoryImpl(
    val transactions: List<Transaction>
) {
    fun prepareMonthlySummary(): MonthlySummary {
        val monthlySummary = MonthlySummary(0.0, 0.0, 0.0)
        return monthlySummary
    }
}