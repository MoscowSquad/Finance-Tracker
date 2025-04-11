package ui

import ReportRepository
import TransactionRepository
import file.UserManager

fun runApp(transactionRepository: TransactionRepository, reportRepository: ReportRepository,  userManager: UserManager){
    MenusManager.startMenu(transactionRepository, reportRepository,  userManager)
}