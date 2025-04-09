enum class Category(val type: TransactionType) {
    Food(TransactionType.EXPENSE),
    Salary(TransactionType.INCOME),
}