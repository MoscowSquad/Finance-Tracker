enum class Category(val type: TransactionType) {
    Food(TransactionType.EXPENSE),
    Rent(TransactionType.EXPENSE),
    Salary(TransactionType.INCOME),
}
