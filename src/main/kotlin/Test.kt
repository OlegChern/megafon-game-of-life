import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import visualization.ConsoleExecutor
import visualization.Life

fun main(args: Array<String>) {

    val life = Life(25, 25, seed = 322, unbounded = true)
    val executor = ConsoleExecutor()

    val worker = executor.runLife(life)
    runBlocking {
        delay(30000)
        worker.cancel()
    }
}