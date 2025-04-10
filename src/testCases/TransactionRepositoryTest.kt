package testCases

import MonthlySummary
import ReportRepositoryImpl
import Transaction
import TransactionRepositoryImpl
import java.time.LocalDateTime

fun main() {
    /// Add functionality
    var transactionRepository = TransactionRepositoryImpl(mutableListOf())
    check(
        testcase = "Add transaction case is valid return true",
        value = transactionRepository.addTransaction(
            Transaction(
                id = 0,
                1000.0,
                TransactionType.EXPENSE,
                Category.Food,
                LocalDateTime.now()
            )
        ),
        expected = true
    )

    check(
        testcase = "The amount value is zero return false",
        value = transactionRepository.addTransaction(
            Transaction(
                id = 0,
                0.0,
                TransactionType.EXPENSE,
                Category.Food,
                LocalDateTime.now()
            )
        ),
        expected = false
    )

    check(
        testcase = "The amount value is negative return false",
        value = transactionRepository.addTransaction(
            Transaction(
                id = -1,
                -10.0,
                TransactionType.EXPENSE,
                Category.Food,
                LocalDateTime.now()
            )
        ),
        expected = false
    )

    check(
        testcase = "The transaction-type is not the same as category-type value is negative return false",
        value = transactionRepository.addTransaction(
            Transaction(
                id = 0,
                100.0,
                TransactionType.EXPENSE,
                Category.Salary,
                LocalDateTime.now()
            )
        ),
        expected = false
    )

    check(
        testcase = "The transaction not added in the current date return false",
        value = transactionRepository.addTransaction(
            Transaction(
                id = 0,
                100.0,
                TransactionType.EXPENSE,
                Category.Salary,
                LocalDateTime.parse("2024-03-03T10:15:30")
            )
        ),
        expected = false
    )

    check(
        testcase = "The transaction not added in the current date return false",
        value = transactionRepository.addTransaction(
            Transaction(
                id = 0,
                100.0,
                TransactionType.EXPENSE,
                Category.Salary,
                LocalDateTime.parse("2125-03-03T10:15:30")
            )
        ),
        expected = false
    )

    // Edit functionality
    transactionRepository = TransactionRepositoryImpl(mutableListOf())
    check(
        testcase = "There is no transactions return false",
        value = transactionRepository.editTransaction(
            Transaction(
                id = 1235,
                1000.0,
                TransactionType.EXPENSE,
                Category.Food,
                LocalDateTime.now()
            )
        ),
        expected = false
    )

    transactionRepository = TransactionRepositoryImpl(
        mutableListOf(
            Transaction(
                1235,
                100.0,
                TransactionType.EXPENSE,
                Category.Food,
                LocalDateTime.parse("2025-03-03T10:15:30")
            ),
            Transaction(
                2345,
                100.0,
                TransactionType.EXPENSE,
                Category.Food,
                LocalDateTime.parse("2025-03-03T10:15:30")
            ),
            Transaction(
                3456,
                100.0,
                TransactionType.EXPENSE,
                Category.Food,
                LocalDateTime.parse("2025-04-03T10:15:30")
            ),
            Transaction(
                5678,
                100.0,
                TransactionType.INCOME,
                Category.Salary,
                LocalDateTime.parse("2025-04-03T10:15:30")
            ),
        )
    )
    check(
        testcase = "The transaction id not found return false",
        value = transactionRepository.editTransaction(
            Transaction(
                id = 8776,
                1000.0,
                TransactionType.EXPENSE,
                Category.Food,
                LocalDateTime.now()
            )
        ),
        expected = false
    )
    check(
        testcase = "The transaction id found return true",
        value = transactionRepository.editTransaction(
            Transaction(
                id = 1235,
                1000.0,
                TransactionType.EXPENSE,
                Category.Food,
                LocalDateTime.now()
            )
        ),
        expected = true
    )
    check(
        testcase = "The transaction id found but the value is zero return false",
        value = transactionRepository.editTransaction(
            Transaction(
                id = 1235,
                0.0,
                TransactionType.EXPENSE,
                Category.Food,
                LocalDateTime.now()
            )
        ),
        expected = false
    )
    check(
        testcase = "The transaction id found but the value is negative return false",
        value = transactionRepository.editTransaction(
            Transaction(
                id = 1235,
                -13.0,
                TransactionType.EXPENSE,
                Category.Food,
                LocalDateTime.now()
            )
        ),
        expected = false
    )
    check(
        testcase = "The transaction id found but the transaction-type is not the same as category-type return false",
        value = transactionRepository.editTransaction(
            Transaction(
                id = 1235,
                13.0,
                TransactionType.INCOME,
                Category.Food,
                LocalDateTime.now()
            )
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
                Category.Food,
                LocalDateTime.parse("2025-03-03T10:15:30")
            ),
            Transaction(
                2345,
                100.0,
                TransactionType.EXPENSE,
                Category.Food,
                LocalDateTime.parse("2025-03-03T10:15:30")
            ),
            Transaction(
                3456,
                100.0,
                TransactionType.EXPENSE,
                Category.Food,
                LocalDateTime.parse("2025-04-03T10:15:30")
            ),
            Transaction(
                5678,
                100.0,
                TransactionType.INCOME,
                Category.Salary,
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