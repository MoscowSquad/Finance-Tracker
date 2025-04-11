enum class Category(val type: TransactionType) {
    Salary(TransactionType.INCOME),
    Freelance(TransactionType.INCOME),
    Investing(TransactionType.INCOME),
    Rent(TransactionType.EXPENSE),
    Transportation(TransactionType.EXPENSE),
    Food(TransactionType.EXPENSE),
}