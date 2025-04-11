package testCases

import Category
import Transaction
import TransactionRepositoryImpl
import TransactionType
import java.time.LocalDateTime

fun main() {
    /// Add functionality
    var transactionRepository = TransactionRepositoryImpl(mutableListOf())
    check(
        testcase = "Add transaction case is valid return true",
        value = transactionRepository.addTransaction(
            1000.0, category = Category.FOOD
        ),
        expected = true
    )

    check(
        testcase = "The amount value is zero return false",
        value = transactionRepository.addTransaction(
            0.0, Category.FOOD
        ),
        expected = false
    )

    check(
        testcase = "The amount value is negative return false",
        value = transactionRepository.addTransaction(
            -10.0, Category.FOOD,
        ),
        expected = false
    )

    check(
        testcase = "The transaction-type is not the same as category-type value is negative return false",
        value = transactionRepository.addTransaction(
            -100.0, Category.SALARY
        ),
        expected = false
    )

    // Edit functionality
    transactionRepository = TransactionRepositoryImpl(mutableListOf())
    check(
        testcase = "There is no transactions return false",
        value = transactionRepository.editTransactionAmount(
            id = 1235, 1000.0
        ),
        expected = false
    )

    transactionRepository = TransactionRepositoryImpl(
        mutableListOf(
            Transaction(
                1235,
                100.0,
                TransactionType.EXPENSE,
                Category.FOOD,
                LocalDateTime.parse("2025-03-03T10:15:30")
            ),
            Transaction(
                2345,
                100.0,
                TransactionType.EXPENSE,
                Category.FOOD,
                LocalDateTime.parse("2025-03-03T10:15:30")
            ),
            Transaction(
                3456,
                100.0,
                TransactionType.EXPENSE,
                Category.FOOD,
                LocalDateTime.parse("2025-04-03T10:15:30")
            ),
            Transaction(
                5678,
                100.0,
                TransactionType.INCOME,
                Category.SALARY,
                LocalDateTime.parse("2025-04-03T10:15:30")
            ),
        )
    )
    check(
        testcase = "The transaction id not found return false",
        value = transactionRepository.editTransactionAmount(
            id = 8776, 1000.0,
        ),
        expected = false
    )
    check(
        testcase = "The transaction id found return true",
        value = transactionRepository.editTransactionAmount(
            id = 1235, 1000.0,
        ),
        expected = true
    )
    check(
        testcase = "The transaction id found but the value is zero return false",
        value = transactionRepository.editTransactionAmount(
            id = 1235, 0.0,
        ),
        expected = false
    )
    check(
        testcase = "The transaction id found but the value is negative return false",
        value = transactionRepository.editTransactionAmount(
            id = 1235, -13.0,
        ),
        expected = false
    )

    // Delete functionality
    transactionRepository = TransactionRepositoryImpl(mutableListOf())
    check(
        testcase = "There is no transactions return false",
        value = transactionRepository.deleteTransaction(1093),
        expected = false
    )

    transactionRepository = TransactionRepositoryImpl(
        mutableListOf(
            Transaction(
                1235,
                100.0,
                TransactionType.EXPENSE,
                Category.FOOD,
                LocalDateTime.parse("2025-03-03T10:15:30")
            ),
            Transaction(
                2345,
                100.0,
                TransactionType.EXPENSE,
                Category.FOOD,
                LocalDateTime.parse("2025-03-03T10:15:30")
            ),
            Transaction(
                3456,
                100.0,
                TransactionType.EXPENSE,
                Category.FOOD,
                LocalDateTime.parse("2025-04-03T10:15:30")
            ),
            Transaction(
                5678,
                100.0,
                TransactionType.INCOME,
                Category.SALARY,
                LocalDateTime.parse("2025-04-03T10:15:30")
            ),
        )
    )
    check(
        testcase = "The transaction id not found return false",
        value = transactionRepository.deleteTransaction(1093),
        expected = false
    )
    check(
        testcase = "The transaction id found return true",
        value = transactionRepository.deleteTransaction(1235),
        expected = true
    )
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