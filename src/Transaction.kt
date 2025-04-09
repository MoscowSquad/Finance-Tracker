import java.time.LocalDateTime

data class Transaction(
    private val id: Int,
    private val amount: Double,
    private val type: TransactionType,
    private val category: Category,
    private val date: LocalDateTime
)