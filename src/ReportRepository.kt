import java.time.LocalDateTime
import java.time.Month

interface ReportRepository {
    fun prepareMonthlySummary(month: Month = LocalDateTime.now().month): MonthlySummary
}