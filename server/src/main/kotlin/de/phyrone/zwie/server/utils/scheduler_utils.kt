package de.phyrone.zwie.server.utils

import com.coreoz.wisp.Scheduler
import com.coreoz.wisp.schedule.Schedule
import com.coreoz.wisp.schedule.Schedules
import kotlinx.coroutines.suspendCancellableCoroutine
import org.apache.commons.lang3.RandomStringUtils
import kotlin.coroutines.resume

suspend fun Scheduler.awaitSchedule(schedule: Schedule) {
    suspendCancellableCoroutine { cont ->
        val jobName = RandomStringUtils.randomAlphabetic(128)
        this.schedule(jobName, { cont.resume(Unit) }, Schedules.executeOnce(schedule))
        cont.invokeOnCancellation { this.cancel(jobName) }
    }
}