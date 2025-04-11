package ui

import ReportRepository
import TransactionRepository
import TransactionType
import file.UserManager
import java.time.LocalDateTime
import java.time.Month

class MenusManager {
    private val transactionProcessor=TransactionProcessor()

    private fun divider(menuName: String){
        println("======== $menuName ========")
    }

    fun startMenu(
        transactionRepository: TransactionRepository,
        reportRepository: ReportRepository,
        userManager: UserManager
    ) {
        if (!userManager.hasUser()) {
            print("Enter your name: ")
            userManager.registerUser(readln())
        }
        println("Hello, ${userManager.userName}")
        while (true){
            print("Welcome to PFT, How i can help you today\n" +
                    "1- Income\n" + // done
                    "2- Expenses\n" +
                    "3- View transaction\n" + //done
                    "4- Exit\n" +
                    "Enter Your option: ")
            when (readln().toIntOrNull()) {
                1 -> incomeMenu(transactionRepository)
                2 -> expensesMenu(transactionRepository)
                3 -> viewTransactionMenu(transactionRepository, reportRepository)
                4 -> break
                null -> println("Invalid Input try again")
                else -> println("Enter a valid number between 1 - 5")
            }
        }
    }

    private fun expensesMenu(transactionRepository: TransactionRepository) {
        while (true){
            divider("Expenses Menu")

            print("1- Add expense\n" + //done
                        "2- View expense transaction\n" + // done
                        "3- Back\n" +
                        "Enter Your option: "
            )
            when (readln().toIntOrNull()) {
                1 -> transactionProcessor.addTransactionMenu(transactionRepository::addTransaction,TransactionType.EXPENSE)
                2 -> transactionProcessor.viewTransaction(transactionRepository,TransactionType.EXPENSE)
                3 -> return
                null -> println("Invalid Input try again")
                else -> println("Enter a valid number between 1 - 3")
            }
        }
    }

    private fun incomeMenu(transactionRepository: TransactionRepository) {
        while (true){
            divider("Income Menu")
            print(
                    "1- Add Income\n" + //done
                    "2- View income transaction\n" + // done
                    "3- Back\n" +
                    "Enter Your option: ")
            when(readln().toIntOrNull()){
                1 -> transactionProcessor.addTransactionMenu(transactionRepository::addTransaction,TransactionType.INCOME)
                2 -> transactionProcessor.viewTransaction(transactionRepository,TransactionType.INCOME)
                3 -> return
                null -> println("Invalid Input try again")
                else -> println("Enter a valid number between 1 - 3")
            }
        }
    }

    private fun viewTransactionMenu(transactionRepository: TransactionRepository, reportRepository: ReportRepository) {
        divider("View Transaction Menu")
        print("Choose report type: \n1. Monthly Transactions \n2. All Transactions\nEnter your option: ")

        var month: Month? = null

        when(readln().toIntOrNull()){
            1 -> {
                month = LocalDateTime.now().month
                print("OK, here is your monthly report:\n")
            }
            2 -> {
                print("OK, here is your report:\n")
                println(transactionRepository.getTransactionsDetails(null))
            }
        }
        val summary = reportRepository.prepareTransactionSummary(transactionRepository.getAllTransactions(), month)
        println(
            """
        |----- Transaction Summary ${month?.name ?: "All Time"} -----
        |Total Income   : $${"%.2f".format(summary.totalIncomes)}
        |Total Expenses : $${"%.2f".format(summary.totalExpenses)}
        |Balance        : $${"%.2f".format(summary.balance)}
        |-------------------------------------------
        """.trimMargin()
        )
    }

}