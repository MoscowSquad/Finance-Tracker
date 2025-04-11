import java.time.LocalDateTime
import java.time.Month

interface ReportRepository {
    fun prepareMonthlySummary(transactions: List<Transaction>, month: Month = LocalDateTime.now().month): MonthlySummary
}