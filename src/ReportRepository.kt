import java.time.LocalDateTime
import java.time.Month

interface ReportRepository {
    fun prepareTransactionSummary(
        transactions: List<Transaction>,
        month: Month? = LocalDateTime.now().month
    ): TransactionSummary
}