package visualization

import kotlinx.coroutines.Job
import life.Life

interface LifeExecutor {
    fun runLife(life: Life): Job
}