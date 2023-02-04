package de.phyrone.zwie.server.misc

import de.phyrone.zwie.server.event.PreShutdownEvent
import de.phyrone.zwie.server.module.DisableTaskRunner
import de.phyrone.zwie.server.module.ModuleLoader
import de.phyrone.zwie.server.utils.logger
import de.phyrone.zwie.shared.common.events.EventBus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext

class ShutdownManager(
    private val moduleLoader: ModuleLoader,
) : CoroutineDispatcher(), KoinComponent {

    private val eventBus by inject<EventBus>()
    private val workerThreads = mutableSetOf<Thread>()

    @Synchronized
    private fun setThreadCount(amount: Int) {
        require(amount >= 0) { "Amount must be >= 0" }
        val rt = Runtime.getRuntime()
        workerThreads.forEach { rt.removeShutdownHook(it) }
        workerThreads.clear()
        repeat(amount) { pos ->
            val wt = WorkerThread(pos + 1)
            workerThreads.add(wt)
            rt.addShutdownHook(wt)
        }
    }

    private val queue = LinkedBlockingQueue<Runnable>()
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        queue.add(block)
    }

    private inner class WorkerThread(private val pos: Int) : Thread("Shutdown-$pos") {
        override fun run() {
            while (true) {
                try {
                    queue.take().run()
                } catch (e: InterruptedException) {
                    break
                } catch (e: Throwable) {
                    logger.atSevere().withCause(e).log("Uncaught Exception in Worker %d", pos)
                }
            }
        }
    }

    fun init() {
        setThreadCount(Runtime.getRuntime().availableProcessors().coerceAtLeast(2))
        Runtime.getRuntime().addShutdownHook(mainShutdownThread)
    }

    private val mainShutdownThread = thread(false, name = "ServerShutdown-Main") {
        runShutdown()
    }

    private fun runShutdown() {

        try {
            try {
                runBlocking { eventBus.post(PreShutdownEvent) }
            } catch (e: Exception) {
                logger.atSevere().withCause(e).log("Error while PreShutdownEvent")
            }
            if (workerThreads.isNotEmpty()) runBlocking(this) { moduleLoader.handleOrdered(DisableTaskRunner) }
            else runBlocking { moduleLoader.handleOrdered(DisableTaskRunner) }
        } finally {
            workerThreads.forEach { it.interrupt() }
            workerThreads.clear()
        }
    }

    companion object {
        private val logger = logger()
    }
}