package ui

import Category
import TransactionRepository
import TransactionType


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
            println("${index + 1}. ${item.title}")
        }
        val category: Category = incomeCategories[readln().toInt() - 1]
        if (addTransaction(
                incomeAmount,
                category
            )
        ) println("Income added successfully") else println("Unexpected Error happened while incoming")
    }


    fun viewIncomeTransaction(transactionRepository: TransactionRepository) {
        print(
            "---------------------------\n" +
                    "Here is all the income\n"
        )
        val incomeTransactionDetails = transactionRepository.getTransactionsDetails(TransactionType.INCOME)
        println(incomeTransactionDetails)
        print(
            "What do you want to do?\n" +
                    "1. Edit\n" + // done
                    "2. Delete\n" + // done
                    "3. Back\n" +
                    "Enter Your option: "
        )
        when (readln().toIntOrNull()) {
            1 -> editIncomeTransaction(transactionRepository)
            2 -> deleteIncomeTransaction(transactionRepository)
            3 -> return
            null -> println("Invalid Input try again")
            else -> println("Enter a valid number between 1 - 5")
        }
    }


    fun deleteIncomeTransaction(transactionRepository: TransactionRepository) {
        print("Enter Transaction ID: ")
        val transactionId: Int = readln().toInt()
        val isTransactionDeleted = transactionRepository.deleteTransaction(transactionId)
        if(isTransactionDeleted) println("Transaction Deleted Successfully") else println("Unexpected Error happened while deleting")
    }

    fun editIncomeTransaction(transactionRepository: TransactionRepository) {
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
                print("Choose income Category\n")
                val incomeCategories = Category.entries.filter { it.type == TransactionType.INCOME }
                incomeCategories.forEachIndexed { index, category ->
                    println("${index + 1}. $category")
                }
                print("Enter Category Number: ")
                val incomeIndex = readln().toInt()
                val editTransactionCategory =
                    transactionRepository.editTransactionCategory(transactionId, incomeCategories[incomeIndex - 1])
                if (editTransactionCategory)
                    println("Category Changed Successfully") else println("Unexpected Error happened while editing")
            }

            null -> println("Invalid Input try again")
            else -> println("Enter a valid number 1 or 2")
        }
    }


}