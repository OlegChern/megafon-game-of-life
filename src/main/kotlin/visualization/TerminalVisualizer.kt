package visualization

import life.GameState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.StringBuilder

const val DELAY_TIME: Long = 3000

class ConsoleExecutor : LifeExecutor {

    private fun visualizeState(state: GameState): String {
        val separator = "=".repeat(state.width * 2 - 1) + "\n"
        val mapFunc = { cell: Boolean -> if (cell) "0" else "~" }
        val view = StringBuilder("")

        view.append(separator)
        state.state.forEach { row ->
            view.append(
                row.joinToString(
                    separator = " ",
                    postfix = "\n",
                    transform = mapFunc)
            )
        }
        view.append(separator)

        return view.toString()
    }

    override fun runLife(life: Life): Job {
        return GlobalScope.launch {
            val states = life.iterator()

            var iteration = 1
            while (true) {
                val current = states.next()

                printHeader(current.width, iteration)
                print(visualizeState(current))

                iteration++
                delay(DELAY_TIME)
            }
        }
    }

    private fun printHeader(width: Int, iteration: Int) {
        val headerTemplate = "%d ITERATION\n"
        val startingPosition = (width * 2 - headerTemplate.length) / 2

        print(" ".repeat(startingPosition))
        print(headerTemplate.format(iteration))
    }
}