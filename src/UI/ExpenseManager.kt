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
        when(readln()){
            "1" -> editExpenseTransaction()
            "2" -> deleteExpenseTransaction()
            "3" -> MenusManager.expensesMenu()
            else -> println("Valid Input try again")
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
        when(readln()){
            "1" -> {
                print("Enter New Amount: ")
                editExpenseTransactionAmount = readln().toDouble()
                // pass to function and update
                println("updated")
            }
            "2" -> {
                print("Chose expense Category\n")
                // view expense category
                println("updated")
            }
        }
    }


}