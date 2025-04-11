package ui

import Category

object IncomeManager {


    fun addIncomeMenu(addTransaction: (Double, Category) -> Boolean) {

        print(
            "---------------------------\n" +
                    "Lets add income\n" +
                    "Enter Amount: "
        )
        val incomeAmount: Double = readln().toDouble()
        print("Choose Income Category\n")
        val incomeCategories = Category.entries.filter { it.type == TransactionType.INCOME }
        incomeCategories.forEachIndexed { index, item ->
            println("${index + 1}. $item")
        }
        val category: Category = incomeCategories[readln().toInt() - 1]
        print(addTransaction(incomeAmount, category))
        // print Income Category

        // pass to add income function
        println("Income added successfully")

    }


    fun viewIncomeTransaction() {
        print(
            "---------------------------\n" +
                    "Here is all the income\n"
        )
        // call view income function
        print(
            "What do you want to do?\n" +
                    "1. Edit\n" + // done
                    "2. Delete\n" + // done
                    "3. Back\n" +
                    "Enter Your option: "
        )
        when (readln().toIntOrNull()) {
            1 -> editIncomeTransaction()
            2 -> deleteIncomeTransaction()
            3 -> return
            null -> println("Invalid Input try again")
            else -> println("Enter a valid number between 1 - 5")
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
        when (readln().toIntOrNull()) {
            1 -> {
                print("Enter New Amount: ")
                editIncomeTransactionAmount = readln().toDouble()
                // pass to function and update
                println("updated")
            }

            2 -> {
                print("Chose income Category\n")
                // view income category
                println("updated")
            }

            null -> println("Invalid Input try again")
            else -> println("Enter a valid number 1 or 2")
        }
    }


}