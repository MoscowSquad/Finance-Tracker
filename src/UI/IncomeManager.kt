package UI

object IncomeManager {



    fun addIncomeMenu() {

        print("---------------------------\n" +
                "Lets add income\n" +
                "Enter Amount: ")
        var incomeAmount: Double = readln().toDouble()

        print("Chose Income Category\n")

        // print Income Category

        // pass to add income function
        println("Income added successfully")

    }


    fun viewIncomeTransaction() {
        print("---------------------------\n" +
                "Here is all the income\n")
        // call view income function
        print("What do you want to do?\n" +
                "1. Edit\n" + // done
                "2. Delete\n" + // done
                "3. Back\n" +
                "Enter Your option: ")
        when(readln()){
            "1" -> editIncomeTransaction()
            "2" -> deleteIncomeTransaction()
            "3" -> MenusManager.incomeMenu()
            else -> println("Valid Input try again")
        }
    }


    fun deleteIncomeTransaction() {
        print("Enter Transaction ID: ")
        var deleteIncomeTransactionId: Int = readln().toInt()
        // this Transaction exist ??
        // call delete fun
    }

    fun editIncomeTransaction() {
        var editIncomeTransactionAmount: Double
        print("Enter Transaction ID: ")
        var editIncomeTransactionID: Int = readln().toInt()
        print("What do you want to edit (1. amount 2. category): ")
        when(readln()){
            "1" -> {
                print("Enter New Amount: ")
                editIncomeTransactionAmount = readln().toDouble()
                // pass to function and update
                println("updated")
            }
            "2" -> {
                print("Chose income Category\n")
                // view income category
                println("updated")
            }
        }
    }




}