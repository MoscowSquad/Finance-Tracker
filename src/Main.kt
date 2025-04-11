import file.FileTransactionManager
import file.StorageOperationManager
import file.UserManager
import ui.runApp
import kotlin.system.exitProcess

fun main() {
    val storageManager = StorageOperationManager
    val userManager = UserManager(storageOperation = storageManager)
    val fileTransactionManager = FileTransactionManager(storageOperation = storageManager)

    val transactions = fileTransactionManager.getAllTransactions()

    val transactionRepository: TransactionRepository = TransactionRepositoryImpl(transactions = transactions.toMutableList())
    val reportRepository: ReportRepository = ReportRepositoryImpl()

    Runtime.getRuntime().addShutdownHook(Thread {
        fileTransactionManager.addAllTransactions(transactionRepository.getAllTransactions(), userManager.userName)
    })

    runApp(transactionRepository, reportRepository, userManager)

    exitProcess(0)
}