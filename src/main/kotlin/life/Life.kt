package life

class Life(state: GameState) : Sequence<GameState> {

    private val stateSequence = generateSequence<GameState>(
        seed = state,
        nextFunction = { evaluateNextState(it) }
    )

    constructor(width: Int, height: Int, seed: Int = 42, density: Float = 0.25f, unbounded: Boolean = false) : this(
        generateStateFromSeed(
            seed,
            density,
            width,
            height,
            unbounded
        )
    )

    override fun iterator(): Iterator<GameState> {
        return stateSequence.iterator()
    }
}