import file.FileTransactionManager
import file.StorageOperationManager
import file.UserManager
import ui.runApp
import kotlin.system.exitProcess

fun main() {
    val storageManager = StorageOperationManager
    val userManager = UserManager(storageOperation = storageManager)
    val fileTransactionManager = FileTransactionManager(storageOperation = storageManager, userManager = userManager)

    val username = userManager.userName
    val transactions = fileTransactionManager.getAllTransactions()

    val transactionRepository: TransactionRepository = TransactionRepositoryImpl(transactions = transactions.toMutableList())

    Runtime.getRuntime().addShutdownHook(Thread {

    })

    runApp(transactionRepository)

    exitProcess(0)
}