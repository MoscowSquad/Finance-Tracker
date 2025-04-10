package UI

object MenusManager {


    fun startMenu(){
        while (true){
            print("Welcome to PFT, How i can help you today\n" +
                    "1- Income\n" + // done
                    "2- Expenses\n" +
                    "3- Categories\n" + // done
                    "4- View transaction\n" + //done
                    "5- Exit\n" +
                    "Enter Your option: ")
            when (readln().toIntOrNull()) {
                1 -> incomeMenu()
                2 -> expensesMenu()
                3 -> categoriesMenu()
                4 -> viewTransactionMenu()
                5 -> break
                null -> println("Invalid Input try again")
                else -> println("Enter a valid number between 1 - 5")
            }
        }
    }

    fun expensesMenu() {
        while (true){
            print(
                "---------------------------\n" +
                        "Expense Menu\n" +
                        "1- Add expense\n" + //done
                        "2- View expense transaction\n" + // done
                        "3- Back\n" +
                        "Enter Your option: "
            )
            when (readln()) {
                "1" -> ExpenseManager.addExpenseMenu()
                "2" -> ExpenseManager.viewExpenseTransaction()
                "3" -> return
                else -> println("Valid Input try again")
            }
        }
    }

    fun incomeMenu() {
        while (true){
            print("---------------------------\n" +
                    "Income Menu\n" +
                    "1- Add Income\n" + //done
                    "2- View income transaction\n" + // done
                    "3- Back\n" +
                    "Enter Your option: ")
            when(readln()){
                "1" -> IncomeManager.addIncomeMenu()
                "2" -> IncomeManager.viewIncomeTransaction()
                "3" -> return
                else -> println("Valid Input try again")
            }
        }
    }


    fun categoriesMenu() {
        while (true){
            print("---------------------------\n" +
                    "Categories Menu\n" +
                    "Here is all the Categories:\n")

            // view all Categories
            print("What do you want to do?\n" +
                    "1. Add\n" + // done
                    "2. Edit\n" + // done
                    "3. Delete\n" +// done
                    "4. Back\n" +// done
                    "Enter Your Option: ")
            when (readln()){
                "1" -> CategoryManger.addCategory()
                "2" -> CategoryManger.editCategory()
                "3" -> CategoryManger.deleteCategory()
                "4" -> return
                else -> println("Valid Input try again")
            }
        }
    }

    fun viewTransactionMenu() {
        print("---------------------------\n" +
                "OK, here is your monthly report:\n")
        // call view function
        print("Do you want to show transactions (y | n): ")
        when(readln().lowercase()){
            "y" -> {
                println("Here is the transactions done this month: ")
                // view month transactions
            }
            "n" -> return
        }
    }

}