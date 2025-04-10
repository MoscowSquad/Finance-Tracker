package file

import Transaction

class `TransactionFileReader Impl`(storagePath: String = "data/transactions.csv") :
    BaseTransactionManager(storagePath),TransactionRetrieverFile  {


    override fun getSpecificTransaction(id: Int): Transaction? {
        return transactions.find { it.id == id }
    }

    override fun getAllTransaction(): List<Transaction> {
        return transactions.toList()
    }

    override fun getMonthlyTransactions(month: Int): List<Transaction> {
        return transactions
            .filter { it.date.monthValue == month }
            .sortedByDescending { it.date }
    }

}



