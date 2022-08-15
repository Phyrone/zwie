package de.phyrone.zwie.server

import de.phyrone.zwie.server.utils.logger
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.concurrent.thread

object ShutdownHandler {
    private val logger = logger()
    private val queue = ConcurrentLinkedQueue<Runnable>()

    init {
        Runtime.getRuntime().addShutdownHook(thread(start = false) { handleShutdown() })
    }

    private fun handleShutdown() {
        while (queue.peek() != null) {
            val runnable = queue.poll()
            try {
                runnable.run()
            } catch (e: Exception) {
                logger.atSevere().withCause(e).log("Error while running shutdown hook")
            }
        }
    }

    fun add(runnable: Runnable) {
        queue.add(runnable)
    }

}