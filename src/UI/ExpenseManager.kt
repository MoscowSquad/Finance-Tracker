package UI

object ExpenseManager {


    fun addExpenseMenu() {
        print("---------------------------\n" +
                "Lets add Expense\n" +
                "Enter Amount: ")
        var expenseAmount: Double = readln().toDouble()
        println("your transaction added successfully ")
        print("Chose Expense Category\n")

        // print Expense Category
        // pass to add Expense function
    }


    fun viewExpenseTransaction() {
        print("---------------------------\n" +
                "Here is all the expense\n")
        // call view expense function
        print("What do you want to do?\n" +
                "1. Edit\n" + // done
                "2. Delete\n" + // done
                "3. Back\n" +
                "Enter Your option: ")
        when(readln().toIntOrNull()){
            1 -> editExpenseTransaction()
            2 -> deleteExpenseTransaction()
            3 -> MenusManager.expensesMenu()
            null->println("Invalid Input try again")
            else -> println("Enter a valid number between 1 - 3")
        }
    }


    fun deleteExpenseTransaction() {
        print("Enter Transaction ID: ")
        var deleteExpenseTransactionId: Int = readln().toInt()
        // this Transaction exist ??
        // call delete fun
    }

    fun editExpenseTransaction() {
        var editExpenseTransactionAmount: Double
        print("Enter Transaction ID: ")
        var editExpenseTransactionID: Int = readln().toInt()
        print("What do you want to edit (1. amount 2. category): ")
        when(readln().toIntOrNull()){
            1 -> {
                print("Enter New Amount: ")
                editExpenseTransactionAmount = readln().toDouble()
                // pass to function and update
                println("updated")
            }
            2 -> {
                print("Chose expense Category\n")
                // view expense category
                println("updated")
            }
            null->println("Invalid Input try again")
            else -> println("Enter a valid number 1 or 2")
        }
    }


}