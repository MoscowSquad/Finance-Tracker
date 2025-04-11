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

    fun loadUserName() {
        val (_, loadedName) = storageOperation.loadFromFile(storagePath)
        userName = loadedName
    }

    private fun hasUser(): Boolean = userName != null

    fun registerUser(inputName: String) {
        if (hasUser()) {
            return
        }
        userName = inputName.ifBlank { "Unknown" }
        storageOperation.saveToFile(emptyList(), storagePath, userName)
    }
}