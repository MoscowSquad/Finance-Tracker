package ui

import ReportRepository
import TransactionRepository

fun runApp(transactionRepository: TransactionRepository, reportRepository: ReportRepository){
    MenusManager.startMenu(transactionRepository, reportRepository)
}