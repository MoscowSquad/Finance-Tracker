package ui

import ReportRepository
import TransactionRepository

object MenusManager {

    private fun divider(menuName: String){
        println("======== $menuName ========")
    }

    fun startMenu(transactionRepository: TransactionRepository, reportRepository: ReportRepository) {
        while (true){
            print("Welcome to PFT, How i can help you today\n" +
                    "1- Income\n" + // done
                    "2- Expenses\n" +
                    "3- Categories\n" + // done
                    "4- View transaction\n" + //done
                    "5- Exit\n" +
                    "Enter Your option: ")
            when (readln().toIntOrNull()) {
                1 -> incomeMenu(transactionRepository)
                2 -> expensesMenu(transactionRepository)
                3 -> categoriesMenu()
                4 -> viewTransactionMenu(reportRepository)
                5 -> break
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
                1 -> ExpenseManager.addExpenseMenu()
                2 -> ExpenseManager.viewExpenseTransaction()
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
                2 -> IncomeManager.viewIncomeTransaction()
                3 -> return
                null -> println("Invalid Input try again")
                else -> println("Enter a valid number between 1 - 3")
            }
        }
    }


    fun categoriesMenu() {
        while (true){
            divider("Categories Menu")
            print(
                    "Here is all the Categories:\n")

            // view all Categories
            print("What do you want to do?\n" +
                    "1. Add\n" + // done
                    "2. Edit\n" + // done
                    "3. Delete\n" +// done
                    "4. Back\n" +// done
                    "Enter Your Option: ")
            when (readln().toIntOrNull()){
                1 -> CategoryManger.addCategory()
                2 -> CategoryManger.editCategory()
                3 -> CategoryManger.deleteCategory()
                4 -> return
                null -> println("Invalid Input try again")
                else -> println("Enter a valid number between 1 - 4")
            }
        }
    }

    fun viewTransactionMenu(reportRepository: ReportRepository) {
        divider("View Transaction Menu")
        print("OK, here is your monthly report:\n")
        // call view function
        print("Do you want to show transactions (1. YES | 2. NO): ")
        when(readln().toIntOrNull()){
            1 -> {
                println("Here is the transactions done this month: ")
                print(reportRepository.prepareMonthlySummary(
                    transactions = TODO(),
                    month = TODO()
                ))
            }
            2 -> return
        }
    }

}