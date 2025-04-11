import ui.runApp

fun main() {

    Runtime.getRuntime().addShutdownHook(Thread {

    })
    runApp()

}