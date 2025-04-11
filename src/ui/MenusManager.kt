package ui

import ReportRepository
import TransactionRepository
import file.UserManager

object MenusManager {

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

    fun expensesMenu(transactionRepository: TransactionRepository) {
        while (true){
            divider("Expenses Menu")

            print("1- Add expense\n" + //done
                        "2- View expense transaction\n" + // done
                        "3- Back\n" +
                        "Enter Your option: "
            )
            when (readln().toIntOrNull()) {
                1 -> ExpenseManager.addExpenseMenu(transactionRepository::addTransaction)
                2 -> ExpenseManager.viewExpenseTransaction(transactionRepository)
                3 -> return
                null -> println("Invalid Input try again")
                else -> println("Enter a valid number between 1 - 3")
            }
        }
    }

    fun incomeMenu(transactionRepository: TransactionRepository) {
        while (true){
            divider("Income Menu")
            print(
                    "1- Add Income\n" + //done
                    "2- View income transaction\n" + // done
                    "3- Back\n" +
                    "Enter Your option: ")
            when(readln().toIntOrNull()){
                1 -> IncomeManager.addIncomeMenu(transactionRepository::addTransaction)
                2 -> IncomeManager.viewIncomeTransaction(transactionRepository)
                3 -> return
                null -> println("Invalid Input try again")
                else -> println("Enter a valid number between 1 - 3")
            }
        }
    }

    fun viewTransactionMenu(transactionRepository: TransactionRepository, reportRepository: ReportRepository) {
        divider("View Transaction Menu")
        print("Choose report type: \n1.Monthly Transactions \n2.All Transactions\nEnter your option: ")
        when(readln().toIntOrNull()){
            1 -> {
                print("OK, here is your monthly report:\n")
                println(reportRepository.prepareMonthlySummary(transactions = transactionRepository.getAllTransactions()))
            }
            2 -> {
                print("OK, here is your report:\n")
                println(transactionRepository.getAllTransactions())
            }
        }
    }

}