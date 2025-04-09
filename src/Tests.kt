fun <T> check(testcase: String, value: T, expected: T): String {
    val green = "\u001B[32m"  // Green for success
    val red = "\u001B[31m"    // Red for failure
    val reset = "\u001B[0m"   // Reset color

    return if (expected == value) {
        "${green}Success${reset}: $testcase"
    } else {
        "${red}Failed${reset}: $testcase"
    }
}