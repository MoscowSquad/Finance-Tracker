package UI

object CategoryManger {

    fun addCategory() {
        var categoryType: String

        print("---------------------------\n" +
                "Lets add new Category\n" +
                "Enter category name: ")

        var newCategory: String = readln()

        print("What is the category type? (1. Income | 2. Expanses) :")

        when(readln().toIntOrNull()){
            1 -> categoryType = "income"
            2 -> categoryType = "expanses"
            null -> println("Invalid Input try again")
            else -> println("Enter a valid number between 1 - 2")
        }

        // add category with by using newCategory & categoryType

    }


    fun editCategory() {
        var editCategoryString: String
        print("---------------------------\n" +
                "Enter category ID: ")
        var editCategoryByID: Int = readln().toInt()

        // this category exist ?? return true or false

        print("What do you want to edit (1 .name | 2. type): ")
        when(readln().toIntOrNull()){
            1 ->  {
                print("Enter Category Name: ")
                editCategoryString = readln().toString()
                // pass this to edit function
                println("updated")
            }
            2 -> {
                print("Enter Category Type: ")
                editCategoryString = readln().toString()
                // pass this to edit function
                println("updated")
            }
            null -> println("Invalid Input try again")
            else -> println("Enter a valid number between 1 - 2")
        }
    }



    fun deleteCategory() {
        print("---------------------------\n" +
                "Enter Category ID: ")
        var editCategoryByID: Int = readln().toInt()
        // this category exist ?? return true | false
        print("Are you sure you want to delete category No.$editCategoryByID (y | n): ")
        when(readln().lowercase()){
            "y" -> {
                println("the Category ${editCategoryByID} is deleted")
                // call delete function
            }
            "n" -> {
                println("Process canceled")
                MenusManager.categoriesMenu()
            }
        }
    }


}