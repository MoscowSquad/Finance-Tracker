package file

import Transaction
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object StorageManager : Storage {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
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

        }
    }

    override fun loadFromFile(storagePath: String): Pair<List<Transaction>, String?> {
        try {
            val file = File(storagePath)
            if (file.exists()) {
                val lines = file.readLines()
                val headerIndex = lines.indexOfFirst { it == TRANSACTION_HEADER }
                if (headerIndex == -1) {
                    return Pair(emptyList(), null)
                }
                val userName = if (headerIndex > 0 && lines[0].startsWith("User: ")) {
                    lines[0].removePrefix("User: ").trim()
                } else {
                    null
                }
                val transactionLines = lines.drop(headerIndex + 1)
                val loadedTransactions = transactionLines.mapNotNull { line ->
                    try {
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
                return Pair(loadedTransactions, userName)
            } else {
                return Pair(emptyList(), null)
            }
        } catch (e: Exception) {
            return Pair(emptyList(), null)
        }
    }
}