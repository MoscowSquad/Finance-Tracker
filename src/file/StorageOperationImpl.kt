package file

import Transaction
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object StorageOperationImpl : StorageOperation {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    private const val TRANSACTION_HEADER = "id,amount,type,category,date"

    override fun saveToFile(transactions: List<Transaction>, storagePath: String, userName: String?) {
        try {
            val file = File(storagePath)
            val additionalData = "User: ${userName ?: "Unknown"}\n"
            val header = "$TRANSACTION_HEADER\n"
            val csvLines = transactions.joinToString(separator = "\n") { transaction ->
                "${transaction.id},${transaction.amount},${transaction.type},${transaction.category},${transaction.date.format(formatter)}"
            }
            file.writeText(additionalData + header + csvLines)
        } catch (e: Exception) {
            println("Exception found: ${e.message}")
        }
    }

    override fun loadTransactionFromFile(storagePath: String): List<Transaction> {
        return withFileLines(storagePath) { lines ->
            val headerIndex = lines.indexOfFirst { it == TRANSACTION_HEADER }
            if (headerIndex == -1) return@withFileLines emptyList()

            val transactionLines = lines.drop(headerIndex + 1)
            transactionLines.mapNotNull { line ->
                parseTransaction(line)
            }
        }
    }
    override fun loadNameFromFile(storagePath: String): String? {
        return withFileLines(storagePath) { lines ->
            lines.firstOrNull { it.startsWith("User: ") }
                ?.removePrefix("User: ")
                ?.trim()
        }
    }

    private fun parseTransaction(line: String): Transaction? {
        return try {
            val parts = line.split(",")
            if (parts.size == 5) {
                Transaction(
                    id = parts[0].toInt(),
                    amount = parts[1].toDouble(),
                    type = TransactionType.valueOf(parts[2]),
                    category = Category.valueOf(parts[3]),
                    date = LocalDateTime.parse(parts[4], formatter)
                )
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}