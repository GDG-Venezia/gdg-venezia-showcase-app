import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

actual fun <T> runTest(block: suspend () -> T) {
    runBlocking { block() }
}
