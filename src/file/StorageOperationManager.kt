package file

import Transaction
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object StorageOperationManager : StorageOperation {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    private const val TRANSACTION_HEADER = "id,amount,type,category,date"


    override fun saveToFile(transactions: List<Transaction>, storagePath: String, userName: String?) {
        try {
            val file = File(storagePath)
            val isNewFile = file.length() == 0L

            val csvLines = transactions.joinToString(separator = "\n") { transaction ->
                "${transaction.id},${transaction.amount},${transaction.type},${transaction.category},${transaction.date.format(formatter)}"
            }

            if (isNewFile) {
                val additionalData = "User: ${userName ?: "Unknown"}\n"
                val header = "$TRANSACTION_HEADER\n"
                file.writeText(additionalData + header + csvLines + "\n")
            } else {
                file.appendText(csvLines + "\n")
            }

        } catch (e: Exception) {

        }
    }


    override fun loadFromFile(storagePath: String): Pair<List<Transaction>, String?> {
        return try {
            val file = File(storagePath)
            if (!file.exists()) return Pair(emptyList(), null)
            val lines = file.readLines()
            val userName = lines.firstOrNull { it.startsWith("User: ") }?.removePrefix("User: ")?.trim()
            val headerIndex = lines.indexOfFirst { it == TRANSACTION_HEADER }
            if (headerIndex == -1) return Pair(emptyList(), userName)
            val transactionLines = lines.drop(headerIndex + 1)
            val loadedTransactions = transactionLines.mapNotNull { line ->
                parseTransaction(line)
            }
            Pair(loadedTransactions, userName)
        } catch (e: Exception) {
            Pair(emptyList(), null)
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