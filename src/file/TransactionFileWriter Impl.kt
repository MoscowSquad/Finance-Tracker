package file

import Transaction

class `TransactionFileWriter Impl`(storagePath: String = "data/transactions.csv") :
    BaseTransactionManager(storagePath), TransactionAdderFile {

    override fun addTransaction(transaction: Transaction) {
        super.addTransaction(transaction)
    }
}