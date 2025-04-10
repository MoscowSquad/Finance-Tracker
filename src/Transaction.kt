import java.time.LocalDateTime

data class Transaction(
    var id: Int,
    val amount: Double,
    val type: TransactionType,
    val category: Category,
    val date: LocalDateTime
)