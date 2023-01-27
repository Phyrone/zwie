import kotlinx.coroutines.runBlocking

actual fun <T> runSuspend(block: suspend () -> T): T = runBlocking { block() }