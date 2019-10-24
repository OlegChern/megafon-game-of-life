package visualization

import kotlinx.coroutines.Job

interface LifeExecutor {
    fun runLife(life: Life): Job
}