package file

import Transaction

class TransactionFileReaderImpl(storagePath: String = "data/transactions.csv") :
    BaseTransactionManager(storagePath),TransactionRetrieverFile  {


    override fun getSpecificTransaction(id: Int): Transaction? {
        return transactions.find { it.id == id }
    }

    override fun getAllTransaction(): List<Transaction> {
        return transactions.toList()
    }

    override fun getMonthlyTransactions(month: Int): List<Transaction> {
        return sortByDateDesc(filterByMonth(month))
    }

    private fun filterByMonth(month: Int): List<Transaction> {
        return transactions.filter { it.date.monthValue == month }
    }

    private fun sortByDateDesc(list: List<Transaction>): List<Transaction> {
        return list.sortedByDescending { it.date }
    }


}



