package testCases

import Category
import MonthlySummary
import ReportRepositoryImpl
import Transaction
import TransactionType
import java.time.LocalDateTime

fun main() {
    check(
        testcase = "There are no transactions in the given month",
        value = ReportRepositoryImpl().prepareMonthlySummary(
            listOf(),
            LocalDateTime.parse("2025-04-03T10:15:30").month
        ),
        expected = MonthlySummary(totalIncomes = 0.0, totalExpenses = 0.0, balance = 0.0)
    )

    var transactions = listOf(
        Transaction(
            1,
            100.0,
            TransactionType.EXPENSE,
            Category.Food,
            LocalDateTime.parse("2025-03-03T10:15:30")
        ),
        Transaction(
            1,
            100.0,
            TransactionType.EXPENSE,
            Category.Food,
            LocalDateTime.parse("2025-02-03T10:15:30")
        ),
        Transaction(
            1,
            100.0,
            TransactionType.EXPENSE,
            Category.Food,
            LocalDateTime.parse("2025-04-03T10:15:30")
        ),
        Transaction(
            1,
            100.0,
            TransactionType.EXPENSE,
            Category.Food,
            LocalDateTime.parse("2025-05-03T10:15:30")
        ),
    )
    check(
        testcase = "There in one transaction in the given month",
        value = ReportRepositoryImpl().prepareMonthlySummary(
            transactions, LocalDateTime.parse("2025-04-03T10:15:30").month
        ),
        expected = MonthlySummary(totalIncomes = 0.0, totalExpenses = 100.0, balance = -100.0)
    )

    transactions = listOf(
        Transaction(1, 100.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-03-03T10:15:30")),
        Transaction(1, 100.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-03-03T10:15:30")),
        Transaction(1, 100.0, TransactionType.INCOME, Category.Salary, LocalDateTime.parse("2025-04-03T10:15:30")),
        Transaction(1, 100.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-05-03T10:15:30")),
    )
    check(
        testcase = "There in one transaction in the given month",
        value = ReportRepositoryImpl().prepareMonthlySummary(
            transactions,
            LocalDateTime.parse("2025-04-03T10:15:30").month
        ),
        expected = MonthlySummary(totalIncomes = 100.0, totalExpenses = 0.0, balance = 100.0)
    )

    transactions = listOf(
        Transaction(1, 100.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-03-03T10:15:30")),
        Transaction(1, 100.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-03-03T10:15:30")),
        Transaction(1, 100.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-04-03T10:15:30")),
        Transaction(1, 100.0, TransactionType.INCOME, Category.Salary, LocalDateTime.parse("2025-04-03T10:15:30")),
    )
    check(
        testcase = "There in one income transaction and one expense transaction in the given month",
        value = ReportRepositoryImpl().prepareMonthlySummary(transactions, LocalDateTime.now().month),
        expected = MonthlySummary(totalIncomes = 100.0, totalExpenses = 100.0, balance = 0.0)
    )

    transactions = listOf(
        Transaction(1, 100.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-03-03T10:15:30")),
        Transaction(1, 100.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-03-03T10:15:30")),
        Transaction(1, 100.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-04-03T10:15:30")),
        Transaction(1, 300.0, TransactionType.INCOME, Category.Salary, LocalDateTime.parse("2025-04-03T10:15:30")),
        Transaction(1, 100.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-04-03T10:15:30")),
        Transaction(1, 100.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-04-03T10:15:30")),
        Transaction(1, 50.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-04-03T10:15:30")),
        Transaction(1, 50.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-05-03T10:15:30")),
        Transaction(1, 50.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-05-03T10:15:30")),
        Transaction(1, 50.0, TransactionType.EXPENSE, Category.Food, LocalDateTime.parse("2025-05-03T10:15:30")),
    )
    check(
        testcase = "There in one income transaction and one expense transaction in the given month",
        value = ReportRepositoryImpl().prepareMonthlySummary(transactions, LocalDateTime.now().month),
        expected = MonthlySummary(totalIncomes = 300.0, totalExpenses = 250.0, balance = 50.0)
    )
}