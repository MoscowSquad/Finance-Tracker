import file.BaseTransactionManager
import Transaction
import TransactionType
import Category
import file.StorageManager
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


val testLoadPath = "test_data/load_test.csv"
val formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
val header = "id,amount,type,category,date"
fun main() {

    val testPath = "test_data/storage_test.csv"
    File(testPath).writeText("")
    val testFilePath = "test_data/transactions_test.txt"
    File(testFilePath).delete()
    val manager = BaseTransactionManager(testFilePath)

    check(
        testcase = "user name should be null at start",
        value = manager.getUserName() == null,
        expected = true
    )

    manager.addUserName(null)

    check(
        testcase = "addUserName should set to Unknown if input is blank",
        value = manager.getUserName() == "Unknown",
        expected = true
    )

    val transaction = Transaction(
        id = 1,
        amount = 100.0,
        type = TransactionType.EXPENSE,
        category = Category.Food,
        date = LocalDateTime.parse("2025-04-11T00:00:00")
    )
    manager.addTransaction(transaction)
    check(
        testcase = "addTransaction should increase list size",
        value = manager.transactions.size == 1,
        expected = true
    )

    val currentName = manager.getUserName()
    manager.addUserName("AnotherUser")
    check(
        testcase = "should not overwrite existing user name",
        value = manager.getUserName() == currentName,
        expected = true
    )

    check(
        testcase = "hasUserName should return true when name is set",
        value = manager.hasUserName(),
        expected = true
    )


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

     StorageManager.saveToFile(transactions, testPath, "Mohammed")

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
        value = content.contains("1,150.0,EXPENSE,Food,2025-04-11 14:00:00"),
        expected = true
    )

    check(
        testcase = "file should contain second transaction data",
        value = content.contains("2,2500.0,INCOME,Salary,2025-04-12 09:30:00"),
        expected = true
    )

    check(
        testcase = "file should handle null username as Unknown",
        value = run {
            val path = "test_data/null_user_test.csv"
            StorageManager.saveToFile(transactions, path, null)
            val result = File(path).readLines().first().trim()
            result == "User: Unknown"
        },
        expected = true
    )

    check(
        testcase = "when empty transaction list should still write user and header only",
        value = run {
            val path = "test_data/empty_test.csv"
            StorageManager.saveToFile(emptyList(), path, "TestUser")
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
            File(path).delete() // تأكد أن الملف غير موجود
            val result = StorageManager.loadFromFile(path)
            result.first.isEmpty() && result.second == null
        },
        expected = true
    )
    check(
        testcase = "when file has no header, should return empty list and null username",
        value = run {
            File(testLoadPath).writeText("Some garbage data\nAnother line")
            val result = StorageManager.loadFromFile(testLoadPath)
            result.first.isEmpty() && result.second == null
        },
        expected = true
    )
    check(
        testcase = "when file has user and valid transactions, should return them correctly",
        value = run {
            val fileContent = """
            User: Mohammed
            $header
            1,100.0,EXPENSE,Food,2025-04-11 14:00:00
            2,2500.0,INCOME,Salary,2025-04-12 10:30:00
        """.trimIndent()
            File(testLoadPath).writeText(fileContent)

            val result = StorageManager.loadFromFile(testLoadPath)
            val transactions = result.first
            val user = result.second

            transactions.size == 2 &&
                    transactions[0].amount == 100.0 &&
                    transactions[1].type == TransactionType.INCOME &&
                    user == "Mohammed"
        },
        expected = true
    )

    check(
        testcase = "when file has header but no user line, username should be null",
        value = run {
            val fileContent = """
            $header
            1,75.5,EXPENSE,Food,2025-04-10 09:00:00
        """.trimIndent()
            File(testLoadPath).writeText(fileContent)

            val result = StorageManager.loadFromFile(testLoadPath)
            val transactions = result.first
            val user = result.second

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
            3,100.0,EXPENSE,Food,2025-04-11 14:00:00
        """.trimIndent()
            File(testLoadPath).writeText(fileContent)

            val result = StorageManager.loadFromFile(testLoadPath)
            result.first.size == 1 && result.first[0].id == 3
        },
        expected = true
    )

}
    fun <T> check(testcase: String, value: T, expected: T): Boolean {
    val green = "\u001B[32m"  // Green for success
    val red = "\u001B[31m"    // Red for failure
    val reset = "\u001B[0m"   // Reset color

    return if (expected == value) {
        println("${green}Success${reset}: $testcase")
        true
    } else {
        println("${red}Failed${reset}: $testcase — Expected $expected but got $value")
        false
    }
}
