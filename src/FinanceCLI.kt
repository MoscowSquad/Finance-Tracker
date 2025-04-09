class FinanceCLI(
) {
    fun start(){
        while (true){
            print("Welcome to PFT, How i can help you today\n" +
                    "1- Income\n" +
                    "2- Expenses\n" +
                    "3- Categories\n" +
                    "4- View transaction\n" +
                    "5- Exit")
            when (readln()) {
                "1" -> incomeMenu()
                "2" -> expencesMenu()
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
        TODO("Not yet implemented")
    }

    private fun expencesMenu() {
        TODO("Not yet implemented")
    }




}