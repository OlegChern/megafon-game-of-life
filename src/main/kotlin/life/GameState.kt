package life

import java.lang.IllegalArgumentException
import kotlin.random.Random

class GameState(val state: Array<Array<Boolean>>, val unbounded: Boolean = false) {
    val height: Int
    val width: Int

    init {
        if (state.isEmpty() || state[0].isEmpty()) {
            throw IllegalArgumentException("Dimensions of the game field cannot be 0")
        }
        height = state.size
        width = state[0].size
    }

    override fun hashCode(): Int {
        return state.contentDeepHashCode() + unbounded.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as GameState

        if (other.unbounded == unbounded && other.state.contentDeepEquals(state))
            return true

        return false
    }
}


private val neighborsToStayAlive = 2..3
private val neighborsToBeBorn = 3..3

fun generateStateFromSeed(seed: Int, density: Float, height: Int, width: Int, unbounded: Boolean): GameState {
    val densityChecked = if (density in -1f..1f) Math.abs(density) else Math.abs(density / Float.MAX_VALUE)

    val cellNumber = Math.round(height * width * densityChecked)
    val random = Random(seed)

    val initState = Array(height) {
        Array(width) { false }
    }

    for (i in 1..cellNumber) {
        val x = Math.abs(random.nextInt() % height)
        val y = Math.abs(random.nextInt() % width)

        initState[x][y] = true
    }
    return GameState(initState, unbounded = unbounded)
}

fun evaluateNextState(state: GameState): GameState {
    val nextState = Array(state.height) { x ->
        Array(state.width) { y ->
            assesNeighbors(x, y, state)
        }
    }
    return GameState(nextState, unbounded = state.unbounded)
}


data class Point(val x: Int, val y: Int)

private fun assesNeighbors(x: Int, y: Int, state: GameState): Boolean {
    val counter = getNeighbors(x, y, state.width, state.height, state.unbounded).count { p -> state.state[p.x][p.y] }

    return when {
        state.state[x][y] && counter in neighborsToStayAlive -> true
        !state.state[x][y] && counter in neighborsToBeBorn -> true
        else -> false
    }
}

private fun getNeighbors(x: Int, y: Int, width: Int, height: Int, unbounded: Boolean): List<Point> {
    var xRange = (x - 1..x + 1).toList()
    var yRange = (y - 1..y + 1).toList()

    if (unbounded) {
        val connect = { coord: Int, size: Int ->
            when (coord) {
                -1 -> size - 1
                size -> 0
                else -> coord
            }
        }
        xRange = xRange.map { connect(it, height) }
        yRange = yRange.map { connect(it, width) }
    } else {
        xRange = xRange.filter { it in 0 until height }
        yRange = yRange.filter { it in 0 until width }
    }

    return xRange.flatMap { i ->
        yRange.map { j -> Point(i, j) }
    }.filter { it != Point(x, y) }
}