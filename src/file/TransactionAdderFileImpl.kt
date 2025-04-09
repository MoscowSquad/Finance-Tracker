package file

import Transaction

class TransactionAdderFileImpl(storagePath: String = "data/transactions.csv") :
    BaseTransactionManager(storagePath), TransactionAdderFile {

    override fun addTransaction(transaction: Transaction) {
        super.addTransaction(transaction)
    }
}