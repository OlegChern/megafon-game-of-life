package visualization

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import life.GameState

class ConsoleExecutorTest : StringSpec() {

    init {
        "Empty field must not contain any cells" {
            val emptyState = GameState(
                arrayOf(
                    arrayOf(false, false, false),
                    arrayOf(false, false, false),
                    arrayOf(false, false, false)
                )
            )
            val expectedResult = "+-----+\n" +
                                 "|     |\n" +
                                 "|     |\n" +
                                 "|     |\n" +
                                 "+-----+\n"

            ConsoleExecutor().visualizeState(emptyState) shouldBe expectedResult
        }

        "Cell symbols must be visualized correctly" {
            val state = GameState(
                arrayOf(
                    arrayOf(true, false, true),
                    arrayOf(false, true, false),
                    arrayOf(true, false, true)
                )
            )
            val expectedResult = "+-----+\n" +
                                 "|*   *|\n" +
                                 "|  *  |\n" +
                                 "|*   *|\n" +
                                 "+-----+\n"

            ConsoleExecutor().visualizeState(state) shouldBe expectedResult
        }
    }
}
