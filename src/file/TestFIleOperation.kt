package file


import Category
import Transaction
import TransactionType
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {

    val testLoadPath = "test_data/load_test.csv"
    val header = "id,amount,type,category,date"
    val testPath = "test_data/storage_test.csv"
    File(testPath).writeText("")
    val testFilePath = "test_data/transactions_test.txt"
    File(testFilePath).delete()

    // saveToFile Function

    val outputFilePath = "test_data/test_save_output.txt"
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    val transactionList = listOf(
        Transaction(1, 150.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-04-11T14:00")),
        Transaction(2, 500.0, TransactionType.INCOME, Category.Salary, LocalDateTime.parse("2025-04-12T10:30"))
    )


    val transactions = listOf(
        Transaction(1, 150.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-04-11T14:00:00")),
        Transaction(2, 2500.0, TransactionType.INCOME, Category.Salary, LocalDateTime.parse("2025-04-12T09:30:00"))
    )

    StorageOperationImpl.saveToFile(transactions, testPath, "Mohammed")

    val file = File(testPath)
    val content = file.readText()

    check(
        testcase = "file should exist after saving",
        value = file.exists(),
        expected = true
    )

    check(
        testcase = "file should start with user line",
        value = content.startsWith("User: Mohammed"),
        expected = true
    )

    check(
        testcase = "file should contain correct header",
        value = content.contains("id,amount,type,category,date"),
        expected = true
    )

    check(
        testcase = "file should contain first transaction data",
        value = content.contains("1,150.0,EXPENSE,Food,2025-04-11T14:00"),
        expected = true
    )

    check(
        testcase = "file should contain second transaction data",
        value = content.contains("2,2500.0,INCOME,Salary,2025-04-11T14:00"),
        expected = true
    )

    check(
        testcase = "file should handle null username as Unknown",
        value = run {
            val path = "test_data/null_user_test.csv"
            StorageOperationImpl.saveToFile(transactions, path, null)
            val result = File(path).readLines().first().trim()
            result == "User: Unknown"
        },
        expected = true
    )

    check(
        testcase = "when empty transaction list should still write user and header only",
        value = run {
            val path = "test_data/empty_test.csv"
            StorageOperationImpl.saveToFile(emptyList(), path, "TestUser")
            val lines = File(path).readLines()
            lines.size == 2 && lines[0].startsWith("User:") && lines[1] == "id,amount,type,category,date"
        },
        expected = true
    )

    // loadFromFile

    check(
        testcase = "when file doesn't exist, should return empty list and null username",
        value = run {
            val path = "test_data/nonexistent.csv"
            File(path).delete()
            val transactions = StorageOperationImpl.loadTransactionFromFile(testLoadPath)
            val user = StorageOperationImpl.loadNameFromFile(testLoadPath)
           transactions.isEmpty() && user == null
        },
        expected = true
    )
    check(
        testcase = "when file has no header, should return empty list and null username",
        value = run {
            File(testLoadPath).writeText("Some garbage data\nAnother line")
            val transactions = StorageOperationImpl.loadTransactionFromFile(testLoadPath)
            val user = StorageOperationImpl.loadNameFromFile(testLoadPath)
            transactions.isEmpty() && user == null
        },
        expected = true
    )
    check(
        testcase = "when file has user and valid transactions, should return them correctly",
        value = run {
            val fileContent = """
            User: Mohammed
            $header
            1,100.0,EXPENSE,Food,2025-04-11T14:00
            2,2500.0,INCOME,Salary,2025-04-11T14:00
        """.trimIndent()
            File(testLoadPath).writeText(fileContent)
            val transactions = StorageOperationImpl.loadTransactionFromFile(testLoadPath)
            val user = StorageOperationImpl.loadNameFromFile(testLoadPath)

            transactions.size == 2 &&
                    transactions[0].amount == 100.0 &&
                    transactions[1].type == TransactionType.INCOME &&
                    user == "Mohammed"  // Fixed expected value
        },
        expected = true
    )

    check(
        testcase = "when file has header but no user line, username should be null",
        value = run {
            val fileContent = """
            $header
            1,75.5,EXPENSE,Food,2025-04-11T14:00
        """.trimIndent()
            File(testLoadPath).writeText(fileContent)


            val transactions = StorageOperationImpl.loadTransactionFromFile(testLoadPath)
            val user = StorageOperationImpl.loadNameFromFile(testLoadPath)

            transactions.size == 1 &&
                    transactions[0].category == Category.Food &&
                    user == null
        },
        expected = true
    )
    check(
        testcase = "when file contains malformed line, should skip that line",
        value = run {
            val fileContent = """
            User: Test
            $header
            this,is,not,a,valid,line
            3,100.0,EXPENSE,Food,2025-04-11T14:00
        """.trimIndent()
            File(testLoadPath).writeText(fileContent)

            val nameResult = StorageOperationImpl.loadNameFromFile(testLoadPath)
            val transactionResult = StorageOperationImpl.loadTransactionFromFile(testLoadPath)
            transactionResult.size == 1 && transactionResult[0].id == 3
        },
        expected = true
    )
    val storagePath = "test_transactions.csv"

    // Clean up before starting tests
    File(storagePath).delete()

    // Test 1: When no user exists initially

    val testManager1 = UserManager(StorageOperationImpl, storagePath)
    check(
        testcase = "when no user exists, userName should be null",
        value = testManager1.userName == null,
        expected = true
    )

    // Test 2: Register empty username
    testManager1.registerUser("")
    check(
        testcase = "when username is empty, should set to Unknown",
        value = testManager1.userName,
        expected = "Unknown"
    )

    // Clean up and create new manager
    File(storagePath).delete()
    val testManager2 = UserManager(StorageOperationImpl, storagePath)

    // Test 3: Register whitespace username
    testManager2.registerUser("   ")
    check(
        testcase = "when username is whitespace, should set to Unknown",
        value = testManager2.userName,
        expected = "Unknown"
    )

    // Clean up and create new manager
    File(storagePath).delete()
    val testManager3 = UserManager(StorageOperationImpl, storagePath)

    // Test 4: Register valid username
    testManager3.registerUser("Mohamed")
    check(
        testcase = "when username is valid, should set correctly",
        value = testManager3.userName,
        expected = "Mohamed"
    )

    // Test 5: Should not overwrite existing user
    testManager3.registerUser("NewName")
    check(
        testcase = "when user exists, should not change username",
        value = testManager3.userName,
        expected = "Mohamed"  // Should stay Mohamed, not change to NewName
    )

    // Clean up after all tests
    File(storagePath).delete()
}

fun <T> check(testcase: String, value: T, expected: T) {
    val green = "\u001B[32m"  // Green for success
    val red = "\u001B[31m"    // Red for failure
    val reset = "\u001B[0m"   // Reset color

    println(
        if (expected == value) {
            "${green}Success${reset}: $testcase"
        } else {
            "${red}Failed${reset}: $testcase"
        }
    )
} 