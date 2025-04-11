import java.time.LocalDateTime
import java.time.Month

interface ReportRepository {
    fun getAllTransactionSummary(transactions: List<Transaction>): MonthlySummary
    fun prepareMonthlySummary(transactions: List<Transaction>, month: Month = LocalDateTime.now().month): MonthlySummary
}