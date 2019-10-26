package life

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class LifeTests : StringSpec() {

    init {
        "Square figure must be static" {
            val testState = GameState(
                arrayOf(
                    arrayOf(false, false, false, false),
                    arrayOf(false, true, true, false),
                    arrayOf(false, true, true, false),
                    arrayOf(false, false, false, false)
                )
            )
            evaluateNextState(testState) shouldBe testState
        }

        "Stick figure must cyclic with period of 2" {
            val vertical = GameState(
                arrayOf(
                    arrayOf(false, true, false),
                    arrayOf(false, true, false),
                    arrayOf(false, true, false)
                )
            )
            val horizontal = GameState(
                arrayOf(
                    arrayOf(false, false, false),
                    arrayOf(true, true, true),
                    arrayOf(false, false, false)
                )
            )
            evaluateNextState(vertical) shouldBe horizontal
            evaluateNextState(horizontal) shouldBe vertical
        }

        "Glider figure must be cyclic with period of 4 and must move towards bottom right corner" {
            val initState = GameState(
                arrayOf(
                    arrayOf(false, true, false, false),
                    arrayOf(false, false, true, false),
                    arrayOf(true, true, true, false),
                    arrayOf(false, false, false, false),
                    arrayOf(false, false, false, false)
                )
            )
            val testState = GameState(
                arrayOf(
                    arrayOf(false, false, false, false),
                    arrayOf(false, false, true, false),
                    arrayOf(false, false, false, true),
                    arrayOf(false, true, true, true),
                    arrayOf(false, false, false, false)
                )
            )
            val testLife = Life(initState)
            val period = 4

            testLife.take(period + 1).last() shouldBe testState
        }

        "Glider figure must return to its initial position if the game field is unbounded" {
            val initState = GameState(
                arrayOf(
                    arrayOf(false, true, false, false, false, false),
                    arrayOf(false, false, true, false, false, false),
                    arrayOf(true, true, true, false, false, false),
                    arrayOf(false, false, false, false, false, false),
                    arrayOf(false, false, false, false, false, false),
                    arrayOf(false, false, false, false, false, false)
                ), unbounded = true
            )
            val testLife = Life(initState)
            val period = 4

            testLife.take(period * 6 + 1).last() shouldBe initState
        }
    }
}