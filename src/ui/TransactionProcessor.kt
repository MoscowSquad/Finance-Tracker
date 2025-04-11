package ui

import Category
import TransactionRepository
import TransactionType

class TransactionProcessor {
    fun addTransactionMenu(addTransaction: (Double, Category) -> Boolean, type:TransactionType) {
        print(
            "---------------------------\n" +
                    "Lets add $type\n" +
                    "Enter Amount: "
        )

        val transactionAmount =  readlnNumber(  "Enter Amount: ")


        val transactionCategories = categorySelector(type)
        val category: Category = transactionCategories[readln().toInt() - 1]
        if (addTransaction(
                transactionAmount,
                category
            )
        ) println("Transaction added successfully") else println("Unexpected Error happened")
    }


    fun viewTransaction(transactionRepository: TransactionRepository, type:TransactionType) {
        print(
            "---------------------------\n" +
                    "Here is all the ${type}s\n"
        )
        val transactionDetails = transactionRepository.getTransactionsDetails(type)
        println(transactionDetails)
        print(
            "What do you want to do?\n" +
                    "1. Edit\n" + // done
                    "2. Delete\n" + // done
                    "3. Back\n" +
                    "Enter Your option: "
        )
        when (readln().toIntOrNull()) {
            1 -> editTransaction(transactionRepository,type)
            2 -> deleteTransaction(transactionRepository)
            3 -> return
            null -> println("Invalid Input try again")
            else -> println("Enter a valid number between 1 - 3")
        }


    }



    private fun deleteTransaction(transactionRepository: TransactionRepository) {
        print("Enter Transaction ID: ")
        val transactionId: Int = readln().toInt()
        val isTransactionDeleted = transactionRepository.deleteTransaction(transactionId)
        if(isTransactionDeleted) println("Transaction Deleted Successfully") else println("Unexpected Error happened while deleting")
    }

    private fun editTransaction(transactionRepository: TransactionRepository, type:TransactionType) {
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
                val transactionCategories =categorySelector(type)

                print("Enter Category Number: ")
                val transactionIndex = readln().toInt()
                val editTransactionCategory =
                    transactionRepository.editTransactionCategory(transactionId, transactionCategories[transactionIndex - 1])
                if (editTransactionCategory)
                    println("Category Changed Successfully") else println("Unexpected Error happened while editing")
            }

            null -> println("Invalid Input try again")
            else -> println("Enter a valid number 1 or 2")
        }
    }

    private fun categorySelector(categoryType:TransactionType):List<Category>{
        print("Choose $categoryType Category\n")
        val transactionCategories = Category.entries.filter {it.type== categoryType }
        transactionCategories.forEachIndexed { index, category ->
            println("${index + 1}. $category")
        }
        return transactionCategories
    }
    fun readlnNumber(msg: String):Double{
        var transactionAmount: Double?
        while (true){
            transactionAmount=  readln().toDoubleOrNull()
            if (transactionAmount != null)break

            println("Invalid Input")
            print(msg)
        }
        return transactionAmount!!
    }

}