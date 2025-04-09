fun main()
{
    val cli: FinanceCLI = FinanceCLI()
    cli.start()
}

class FinanceCLI(
) {
    fun start(){
        while (true){
            print("Welcome to PFT, How i can help you today\n" +
                    "1- Income\n" + // done
                    "2- Expenses\n" +
                    "3- Categories\n" + // done
                    "4- View transaction\n" +
                    "5- Exit\n" +
                    "Enter Your option: ")
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

    private fun expensesMenu() {
        TODO("Not yet implemented")
    }

    private fun incomeMenu() {
        print("---------------------------\n" +
                "Income Menu\n" +
                "1- Add Income\n" + //done
                "2- View income transaction\n" +
                "3- Back\n" +
                "Enter Your option: ")
        when(readln()){
            "1" -> addIncomeMenu()
            "2" -> viewIncomeTransaction()
            "3" -> start()
            else -> println("Valid Input try again")
        }
    }

    private fun viewIncomeTransaction() {
        print("---------------------------\n" +
                "Here is all the income\n")
        // call view income function
        print("What do you want to do?\n" +
                "1. Edit\n" +
                "2. Delete\n" +
                "3. Back\n" +
                "Enter Your option: ")
        when(readln()){
            "1" -> editIncomeTransaction()
            "2" -> deleteIncomeTransaction()
            "3" -> incomeMenu()
            else -> println("Valid Input try again")
        }
    }

    private fun deleteIncomeTransaction() {
        TODO("Not yet implemented")
    }

    private fun editIncomeTransaction() {
        var editIncomeTransactionID: Int
        var editIncomeTransactionAmount: Double
        print("Enter Transaction ID: ")
        editIncomeTransactionID = readln().toInt()
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

    private fun addIncomeMenu() {
        var incomeAmount: Double
        print("---------------------------\n" +
                "Lets add income\n" +
                "Enter Amount: ")
        incomeAmount = readln().toDouble()
        print("Chose Income Category\n")

        // print Income Category
        // pass to add income function
    }

    private fun viewTransaction() {

    }

    private fun categoriesMenu() {
        print("---------------------------\n" +
                "Categories Menu\n" +
                "Here is all the Categories:\n")

        // view all Categories
        print("What do you want to do?\n" +
                "1. Add\n" + // done
                "2. Edit\n" + // done
                "3. Delete\n" +// done
                "3. Back\n" +// done
                "Enter Your Option: ")
        when (readln()){
            "1" -> addCategory()
            "2" -> editCategory()
            "3" -> deleteCategory()
            "4" -> start()
            else -> println("Valid Input try again")
        }
    }

    private fun editCategory() {
        var editCategoryByID: Int
        var editCategoryString: String
        print("---------------------------\n" +
                "Enter category ID: ")
        editCategoryByID = readln().toInt()

        // this category exist ?? return true or false

        print("What do you want to edit (1 .name | 2. type): ")
        when(readln()){
            "1" ->  {
                print("Enter Category Name: ")
                editCategoryString = readln().toString()
                // pass this to edit function
                println("updated")
            }
            "2" -> {
                print("Enter Category Type: ")
                editCategoryString = readln().toString()
                // pass this to edit function
                println("updated")
            }
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

        // add category with by using newCategory & categoryType

    }

    private fun deleteCategory() {
        var editCategoryByID: Int
        print("---------------------------\n" +
                "Enter Category ID: ")
        editCategoryByID = readln().toInt()
        // this category exist ?? return true | false
        print("Are you sure you want to delete category No.$editCategoryByID (y | n): ")
        when(readln().lowercase()){
            "y" -> {
                println("the Category ${editCategoryByID} is deleted")
                // call delete function
            }
            "n" -> {
                println("Process canceled")
                categoriesMenu()
            }
        }
    }
}
// ---------------------------