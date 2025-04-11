package file

import java.io.File

public inline fun <T> withFileLines(
    storagePath: String,
    block: (List<String>) -> T
): T {
    return try {
        val file = File(storagePath)
        if (!file.exists()) block(emptyList())
        else block(file.readLines())
    } catch (e: Exception) {
        block(emptyList())
    }
}