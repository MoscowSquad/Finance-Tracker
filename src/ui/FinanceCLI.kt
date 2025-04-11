package ui

import ReportRepository
import TransactionRepository
import file.UserManager

fun runApp(transactionRepository: TransactionRepository, reportRepository: ReportRepository,  userManager: UserManager){
    val menusManager =MenusManager()
    menusManager.startMenu(transactionRepository, reportRepository,  userManager)
}