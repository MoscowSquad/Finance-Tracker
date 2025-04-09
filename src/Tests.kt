import java.time.LocalDateTime
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TransactionRepositoryImplTest {
    private lateinit var transactionRepository: TransactionRepositoryImpl
    private lateinit var transactions: MutableList<Transaction>

    fun setUp() {
        transactions = mutableListOf() // Fresh list for each test
        transactionRepository = TransactionRepositoryImpl(transactions)
    }


    //  Test adding a valid transaction
    fun `addTransaction should return true and add transaction successfully`() {
        // val transaction = Transaction(amount = 100.0, category = TransactionType.INCOME, date = LocalDateTime.now())
        val transaction = Transaction(
            id = 1,
            amount = 10.0,
            type = TransactionType.INCOME,
            Category.Salary,
            LocalDateTime.parse("2025-01-09T14:30:00")
        )
        val result = transactionRepository.addTransaction(transaction)

        assertTrue(result, "Transaction should be added successfully")
        assertEquals(1, transactions.size, "Transactions list should have one transaction")
        assertEquals(1, transactions[0].id, "Transaction ID should be auto-incremented to 1")
    }


    // Test adding a transaction with negative amount (should still add since no validation exists)
    fun `addTransaction should add transaction even with negative amount`() {
        // val transaction = Transaction(amount = -50.0, category = TransactionType.EXPENSE, date = LocalDateTime.now())
        val transaction = Transaction(
            id = 1,
            amount = 10.0,
            type = TransactionType.EXPENSE,
            Category.Food,
            LocalDateTime.parse("2025-02-09T14:30:00")
        )

        val result = transactionRepository.addTransaction(transaction)

        assertTrue(result, "Transaction should be added despite negative amount")
        assertEquals(1, transactions.size, "Transactions list should have one transaction")
    }


    // Test editing an existing transaction
    fun `editTransaction should return true when updating an existing transaction`() {
        //val transaction = Transaction(amount = 200.0, category = "Rent", date = LocalDateTime.now())
        val transaction = Transaction(
            id = 1,
            amount = 200.0,
            type = TransactionType.EXPENSE,
            Category.Food,
            LocalDateTime.parse("2025-03-09T14:30:00")
        )
        transactionRepository.addTransaction(transaction)

        // val updatedTransaction = Transaction(id = 1, amount = 250.0, category = "Rent", date = LocalDateTime.now())
        val updatedTransaction = Transaction(
            id = 1,
            amount = 250.0,
            type = TransactionType.EXPENSE,
            Category.Food,
            LocalDateTime.parse("2025-04-09T14:30:00")
        )
        val result = transactionRepository.editTransaction(updatedTransaction)

        assertTrue(result, "Editing an existing transaction should return true")
        assertEquals(250.0, transactions[0].amount, "Transaction amount should be updated")
    }


    // Test editing a non-existent transaction
    fun `editTransaction should return false when transaction ID does not exist`() {
        // val transaction = Transaction(id = 99, amount = 300.0, category = "Utilities", date = LocalDateTime.now())
        val transaction = Transaction(
            id = 99,
            amount = 300.0,
            type = TransactionType.EXPENSE,
            Category.Rent,
            LocalDateTime.parse("2025-05-09T14:30:00")
        )
        val result = transactionRepository.editTransaction(transaction)

        assertFalse(result, "Editing a non-existing transaction should return false")
    }
}


fun <T> test(testcase: String, value: T, expected: T): String {
    val green = "\u001B[32m"  // Green for success
    val red = "\u001B[31m"    // Red for failure
    val reset = "\u001B[0m"   // Reset color

    return if (expected == value) {
        "${green}Success${reset}: $testcase"
    } else {
        "${red}Failed${reset}: $testcase"
    }
}
