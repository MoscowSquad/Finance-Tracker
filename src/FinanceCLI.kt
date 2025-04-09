class FinanceCLI(
) {
    fun start(){
        while (true){
            println("Welcome to PFT, How i can help you today\n" +
                    "1- Income\n" +
                    "2- Expences\n" +
                    "3- Categories\n" +
                    "4- View transaction\n" +
                    "5- Exit")
            when (readln()) {
                "1" -> incomeMenu()
                "2" -> expencesMenu()
                "3" -> categoriesMenu()
                "4" -> viewTransaction()
                "5" -> break
                else -> println("Valid Input")
            }
        }
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

    private fun incomeMenu() {
        TODO("Not yet implemented")
    }


}