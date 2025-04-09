package file

import Transaction
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object StorageManager {

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")


    fun saveToFile(transactions: List<Transaction>, storagePath: String) {
        try {
            val file = File(storagePath)
            val header = "id,amount,type,category,date\n"
            val csvLines = transactions.joinToString(separator = "\n") { transaction ->
                "${transaction.id},${transaction.amount},${transaction.type},${transaction.category},${transaction.date.format(formatter)}"
            }
            file.writeText(header + csvLines)
            println("Data saved successfully")
        } catch (e: Exception) {
            println("Error saving to file: ${e.message}")
        }
    }

    fun loadFromFile(storagePath: String): List<Transaction> {
        try {
            val file = File(storagePath)
            if (file.exists()) {
                val lines = file.readLines()
                val transactionLines = if (lines.isNotEmpty()) lines.drop(1) else emptyList()
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
                        println("Error parsing line: $line - ${e.message}")
                        null
                    }
                }
                println("Data loaded successfully from $storagePath")
                return loadedTransactions
            } else {
                println("No existing data file found at $storagePath")
                return emptyList()
            }
        } catch (e: Exception) {
            println("Error loading from file: ${e.message}")
            return emptyList()
        }
    }
}