import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import visualization.ConsoleExecutor

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import com.xenomachina.argparser.mainBody

import life.Life

fun main(args: Array<String>) = mainBody {
    val parsedArgs = ArgParser(args).parseInto(::ApplicationArgs)

    val life = Life(parsedArgs.width, parsedArgs.height, parsedArgs.seed, parsedArgs.density, parsedArgs.unbounded)
    val exec = ConsoleExecutor()

    val worker = exec.runLife(life)

    readLine()

    worker.cancel()
}

private class ApplicationArgs(parser: ArgParser) {

    val height by parser.storing(
        "-r", "--rows",
        help = "Height of the game field. Must be a positive integer."
    ) { toInt() }.default(25)

    val width by parser.storing(
        "-c", "--columns",
        help = "Width of the game field. Must be a positive integer."
    ) { toInt() }.default(25)

    val seed by parser.storing(
        "-s", "--seed",
        help = "Seed to generate an initial game state from. Must be an integer"
    ) { toInt() }.default(322)

    val density by parser.storing(
        "-d", "--density",
        help = "Determines the amount of living cells in the initial state. " +
                "Should be a float from 0 to 1. If value is given from another range, " +
                "it will be scaled to [0, 1]."
    ) { toFloat() }.default(0.25f)

    val unbounded by parser.flagging(
        "-u", "--unbounded",
        help = "Determines if the game field is enclosed or not."
    ).default(false)
}
