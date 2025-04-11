package ui

import Category
import TransactionRepository

object ExpenseManager {


    fun addExpenseMenu(addTransaction: (Double, Category) -> Boolean) {
        print("---------------------------\n" +
                "Lets add Expense\n" +
                "Enter Amount: ")

        val expenseAmount: Double = readln().toDouble()
        print("Choose Expense Category\n")
        val expenseCategories = Category.entries.filter { it.type == TransactionType.EXPENSE }
        expenseCategories.forEachIndexed { index, item ->
            println("${index + 1}. $item")
        }
        val category: Category = expenseCategories[readln().toInt() - 1]
        if (addTransaction(
                expenseAmount,
                category
            )
        ) println("Expense added successfully") else println("Unexpected Error happened while expensing")
    }


    fun viewExpenseTransaction(transactionRepository: TransactionRepository) {
        print("---------------------------\n" +
                "Here is all the expense\n")
        val expenseTransactionDetails = transactionRepository.getTransactionsDetails(TransactionType.EXPENSE)
        println(expenseTransactionDetails)
        print("What do you want to do?\n" +
                "1. Edit\n" + // done
                "2. Delete\n" + // done
                "3. Back\n" +
                "Enter Your option: ")
        when(readln().toIntOrNull()){
            1 -> editExpenseTransaction(transactionRepository)
            2 -> deleteExpenseTransaction(transactionRepository)
            3 -> return
            null->println("Invalid Input try again")
            else -> println("Enter a valid number between 1 - 3")
        }
    }


    fun deleteExpenseTransaction(transactionRepository: TransactionRepository) {
        print("Enter Transaction ID: ")
        val transactionId: Int = readln().toInt()
        val isTransactionDeleted = transactionRepository.deleteTransaction(transactionId)
        if(isTransactionDeleted) println("Transaction Deleted Successfully") else println("Unexpected Error happened while deleting")
    }

    fun editExpenseTransaction(transactionRepository: TransactionRepository) {
        print("Enter Transaction ID: ")
        val transactionId: Int = readln().toInt()
        println("What do you want to edit?\n1. Amount\n2. category")
        when (readln().toIntOrNull()) {
            1 -> {
                print("Enter New Amount: ")
                val amount: Double = readln().toDouble()
                val editTransactionAmount = transactionRepository.editTransactionAmount(transactionId, amount)
                if (editTransactionAmount)
                    println("Amount Updated Successfully") else println("Unexpected Error happened while editing")
            }

            2 -> {
                print("Choose Expense Category\n")
                val expenseCategories = Category.entries.filter { it.type == TransactionType.EXPENSE }
                expenseCategories.forEachIndexed { index, category ->
                    println("${index + 1}. $category")
                }
                print("Enter Category Number: ")
                val expenseIndex = readln().toInt()
                val editTransactionCategory =
                    transactionRepository.editTransactionCategory(transactionId, expenseCategories[expenseIndex - 1])
                if (editTransactionCategory)
                    println("Category Changed Successfully") else println("Unexpected Error happened while editing")
            }

            null -> println("Invalid Input try again")
            else -> println("Enter a valid number 1 or 2")
        }
    }


}