import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

@TestInstance(TestInstance.Lifecycle.PER_METHOD)  // New instance for every test
class TransactionRepositoryImplTest {
    private lateinit var transactionRepository: TransactionRepositoryImpl
    private lateinit var transactions: MutableList<Transaction>

    @BeforeEach
    fun setUp() {
        transactions = mutableListOf() // Fresh list for each test
        transactionRepository = TransactionRepositoryImpl(transactions)

        assertTrue(transactionRepository.getTransactions().isEmpty(), "Repository should be empty at the start of each test.")
        println("Repository reset: ${transactionRepository.getTransactions().size} transactions")

    }


    @Test
    fun `getTransactions should return empty list initially`() {
        assertTrue(transactionRepository.getTransactions().isEmpty(), "Repository should be empty at the start of each test.")
    }

    @Test
    //  Test adding a valid transaction
    fun `addTransaction should return true and add transaction successfully`() {
        val transaction = Transaction(
            id = 1, // Placeholder, the `id` will be assigned automatically
            amount = 10.0,
            type = TransactionType.INCOME,
            category = Category.Salary,
            date = LocalDateTime.parse("2025-01-09T14:30:00")
        )
        val result = transactionRepository.addTransaction(transaction)

        assertTrue(result, "Transaction should be added successfully")
        assertEquals(1, transactions.size, "Transactions list should have one transaction")
        assertEquals(1, transactions[0].id, "Transaction ID should be auto-incremented to 1")
    }

    @Test
    // Test adding a transaction with negative amount (should still add since no validation exists)
    fun `addTransaction should add transaction even with negative amount`() {
        val transaction_1 = Transaction(
            id = 2, // Placeholder, the `id` will be assigned automatically
            amount = -50.0,
            type = TransactionType.EXPENSE,
            category = Category.Food,
            date = LocalDateTime.parse("2025-02-09T14:30:00"),
        )
        val result_1 = transactionRepository.addTransaction(transaction_1)

        val transaction_2 = Transaction(
            id = 3, // Placeholder, the `id` will be assigned automatically
            amount = -10.0,
            type = TransactionType.EXPENSE,
            category = Category.Food,
            date = LocalDateTime.parse("2025-03-09T14:30:00")
        )
        val result_2 = transactionRepository.addTransaction(transaction_2)

        assertTrue(result_1, "Transaction should be added despite negative amount")
        assertTrue(result_2, "Transaction should be added despite negative amount")

        //assertEquals(2, transactions.size, "Transactions list should have one transaction")

        assertEquals(2, transactions.size, "Transactions list should have two transactions")
        println("Transactions in list: ${transactionRepository.getTransactions().size}") // Debug output
        assertEquals(1, transactions[0].id, "First transaction ID should be 1")
        assertEquals(2, transactions[1].id, "Second transaction ID should be 2")
    }

    @Test
    fun `addTransaction should return false when transaction ID is 0`() {
        val transaction = Transaction(
            id = 0, // Invalid ID
            amount = 100.0,
            type = TransactionType.INCOME,
            category = Category.Salary,
            date = LocalDateTime.parse("2025-01-10T14:30:00")
        )
        val result = transactionRepository.addTransaction(transaction)

        assertFalse(result, "Transaction with ID 0 should not be added")
    }



    @Test
    // Test editing an existing transaction
    fun `editTransaction should return true when updating an existing transaction`() {
        val transaction = Transaction(
            id = 1, // Placeholder, the `id` will be assigned automatically
            amount = 200.0,
            type = TransactionType.EXPENSE,
            category = Category.Food,
            date = LocalDateTime.parse("2025-04-01T14:30:00")
        )
        transactionRepository.addTransaction(transaction)

        val updatedTransaction = Transaction(
            id = 1, // Placeholder, the `id` will be assigned automatically
            amount = 250.0,
            type = TransactionType.EXPENSE,
            category = Category.Rent,
            date = LocalDateTime.parse("2025-04-02T14:30:00")
        )
        val result = transactionRepository.editTransaction(updatedTransaction)

        assertTrue(result, "Editing an existing transaction should return true")
        assertEquals(250.0, transactions[0].amount, "Transaction amount should be updated")
    }

    @Test
    // Test editing a non-existent transaction
    fun `editTransaction should return false when transaction ID does not exist`() {
        val transaction = Transaction(
            id = 0, // Placeholder, the `id` will be assigned automatically
            amount = 300.0,
            type = TransactionType.EXPENSE,
            category = Category.Rent,
            date = LocalDateTime.parse("2025-04-09T14:30:00")
        )
        val result = transactionRepository.editTransaction(transaction)

        assertFalse(result, "Editing a non-existing transaction should return false")
    }


    @Test
    fun `editTransaction should return false if no changes are made`() {
        val transaction = Transaction(
            id = 1,
            amount = 100.0,
            type = TransactionType.INCOME,
            category = Category.Salary,
            date = LocalDateTime.parse("2025-01-09T14:30:00")
        )
        transactionRepository.addTransaction(transaction)

        val unchangedTransaction = Transaction(
            id = 1,
            amount = 100.0,
            type = TransactionType.INCOME,
            category = Category.Salary,
            date = LocalDateTime.parse("2025-01-09T14:30:00")
        )

        val result = transactionRepository.editTransaction(unchangedTransaction)

        assertFalse(result, "Editing with no changes should return false")
    }




}
