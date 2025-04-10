fun main(){
    val transactionRepositoryImplTest = TransactionRepositoryImplTest()

    transactionRepositoryImplTest.setUp()

    transactionRepositoryImplTest.`addTransaction should return true and add transaction successfully`()
    transactionRepositoryImplTest.`addTransaction should add transaction even with negative amount`()
    transactionRepositoryImplTest.`addTransaction should return false when transaction ID is 0`()

    transactionRepositoryImplTest.`editTransaction should return false when transaction ID does not exist`()
    transactionRepositoryImplTest.`editTransaction should return true when updating an existing transaction`()
    transactionRepositoryImplTest.`editTransaction should return false if no changes are made`()

    transactionRepositoryImplTest.`getTransactions should return empty list initially`()
}

fun <T> test(testcase: String, value: T, expected: T): String {
    val green = "\u001B[32m"  // Green for success
    val red = "\u001B[31m"    // Red for failure
    val reset = "\u001B[0m"   // Reset color

    return if (expected == value) {
        "${green}Success${reset}: $testcase"
    } else {
        "${red}Failed${reset}: $testcase"
    }
}
