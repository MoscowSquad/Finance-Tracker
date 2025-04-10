import java.time.LocalDateTime

public class TransactionRepositoryImpl(
    private val transactions: MutableList<Transaction>
): TransactionRepository {

    // Getter method to expose transactions for testing
    fun getTransactions(): List<Transaction> {
        return transactions
    }

    // Method to add a transaction with flexible handling of negative amounts
    override fun addTransaction(transaction: Transaction): Boolean {
        return try {
            // Check if transaction ID is 0, which is invalid
            if (transaction.id == 0) {
                println("Error: Transaction ID cannot be 0.")
                return false
            }

            // Option 1: Handle negative amounts (Allow negative amounts)
            if (transaction.amount < 0) {
                // You could log the negative amount transaction or just allow it
                println("Warning: Negative transaction amount detected, allowing it.")
                // If you want to allow the negative amount, you can just return true
                // and add the transaction like usual, or skip the addition based on your preference.
                // For now, we will add the transaction with the usual logic.
            }

            // Assign a unique ID based on the highest existing ID
            val newId = if (transactions.isEmpty()) 1 else transactions.maxOf { it.id } + 1

            // Ensure that the transaction ID is updated with the newly generated ID (if needed)
            val newTransaction = transaction.copy(id = newId)

            transactions.add(newTransaction)
            println("Transaction added with ID: $newId") // Debug output
            println("Transactions list size after adding: ${transactions.size}") // Debug output
            println("Transaction added successfully")
            true

        } catch (e: IllegalArgumentException) {
            // Specific exception for invalid arguments
            println("Error adding transaction: Invalid argument - ${e.message}")
            false

        } catch (e: Exception) {
            // General exception for other unexpected errors
            println("Error adding transaction: ${e.message}")
            false
        }
    }

    // Method to edit an existing transaction
    override fun editTransaction(transaction: Transaction): Boolean {
        if (transaction.id == 0) {
            println("Error: Invalid transaction ID 0.")
            return false
        }

        val index = transactions.indexOfFirst { it.id == transaction.id }
        return if (index != -1) {
            // Check if the transaction actually changed before updating
            val existingTransaction = transactions[index]
            if (existingTransaction == transaction) {
                println("No changes detected in the transaction.")
                return false
            }

            // Perform the update with specific fields if necessary
            transactions[index] = transaction
            println("Transaction with ID ${transaction.id} updated successfully")
            true
        } else {
            println("Error: Transaction with ID ${transaction.id} not found")
            false
        }
    }
}
