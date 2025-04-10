package file

class UserManager(
    private val storageOperation: StorageOperation,
    private val storagePath: String= "data/transactions.csv"
) {
    var userName: String? = null
        private set

    init {
        loadUserName()
    }

    private fun loadUserName() {
        val (_, loadedName) = storageOperation.loadFromFile(storagePath)
        userName = loadedName
    }

    fun hasUser(): Boolean = userName != null

    fun registerUser(inputName: String) {
        if (hasUser()) {
            return
        }
        userName = if (inputName.isBlank()) "Unknown" else inputName
        storageOperation.saveToFile(emptyList(), storagePath, userName)

    }
}