package visualization

import life.GameState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import life.Life
import org.fusesource.jansi.AnsiConsole
import java.lang.StringBuilder

const val DELAY_TIME: Long = 100

class ConsoleExecutor : LifeExecutor {

    fun visualizeState(state: GameState): String {
        val separator = "+" + "-".repeat(state.width * 2 - 1) + "+\n"
        val mapFunc = { cell: Boolean -> if (cell) "*" else " " }
        val view = StringBuilder("")

        view.append(separator)
        state.state.forEach { row ->
            view.append(
                row.joinToString(
                    separator = " ",
                    prefix = "|",
                    postfix = "|\n",
                    transform = mapFunc
                )
            )
        }
        view.append(separator)

        return view.toString()
    }

    override fun runLife(life: Life): Job {
        return GlobalScope.launch {
            val states = life.iterator()
            var current = states.next()

            val eraser = "\u001b[H\u001b[2J"
            AnsiConsole.systemInstall()

            while (true) {
                AnsiConsole.out.print(eraser)

                print(visualizeState(current))
                current = states.next()

                delay(DELAY_TIME)
            }
        }
    }
}