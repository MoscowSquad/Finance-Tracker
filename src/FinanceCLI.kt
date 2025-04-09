class FinanceCLI(
) {
    fun start(){
        while (true){
            print("Welcome to PFT, How i can help you today\n" +
                    "1- Income\n" + // done
                    "2- Expenses\n" +
                    "3- Categories\n" + // done
                    "4- View transaction\n" +
                    "5- Exit")
            when (readln()) {
                "1" -> incomeMenu()
                "2" -> expensesMenu()
                "3" -> categoriesMenu()
                "4" -> viewTransaction()
                "5" -> break
                else -> println("Valid Input try again")
            }
        }
    }

    private fun incomeMenu() {
        print("---------------------------\n" +
                "Income Menu\n" +
                "1- Add Income\n" +
                "2- View income transaction\n" +
                "3- Back\n" +
                "Enter Your option: ")
        when(readln()){
            "1" -> addIncomeMenu()
            "2" -> viewIncomeTransation()
            "3" -> start()
            else -> println("Valid Input try again")
        }
    }

    private fun viewIncomeTransation() {
        TODO("Not yet implemented")
    }

    private fun addIncomeMenu() {
        TODO("Not yet implemented")
    }

    private fun viewTransaction() {
        TODO("Not yet implemented")
    }

    private fun categoriesMenu() {
        print("---------------------------\n" +
                "Categories Menu\n" +
                "Here is all the Categories:\n")

        // view all Categories
        print("What do you want to do?\n" +
                "1. Add\n" +
                "2. Edit\n" +
                "3. Delete\n" +
                "3. Back\n" +
                "Enter Your Option: ")
        when (readln()){
            "1" -> addCategory()
            "2" -> editCategory()
            "3" -> deleteCategory()
            "4" -> start()
            else -> println("Valid Input try again")
        }
    }
    private fun addCategory() {
        var newCategory: String
        var categoryType: String

        print("---------------------------\n" +
                "Lets add new Category\n" +
                "Enter category name: ")

        newCategory = readln()

        print("What is the category type? (i. Income | e. Expanses) :")

        when(readln().lowercase()){
            "i" -> categoryType = "income"
            "e" -> categoryType = "expanses"
            else -> println("Valid Input try again")
        }
    }
    private fun deleteCategory() {
        TODO("Not yet implemented")
    }

    private fun editCategory() {
        TODO("Not yet implemented")
    }



    private fun expensesMenu() {
        print("---------------------------\n" +
                "Expenses Menu\n")
    }




}