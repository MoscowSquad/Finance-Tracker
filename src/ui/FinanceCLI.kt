package ui

import ReportRepository
import TransactionRepository

fun runApp(transactionRepository: TransactionRepository){
    MenusManager.startMenu(transactionRepository)
}