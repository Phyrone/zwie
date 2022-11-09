package de.phyrone.zwie.server.misc

import de.phyrone.zwie.server.module.DisableTaskRunner
import de.phyrone.zwie.server.module.ModuleLoader
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.runBlocking
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext

class ShutdownManager(
    private val moduleLoader: ModuleLoader,
) : CoroutineDispatcher() {

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

    private inner class WorkerThread(pos: Int) : Thread("Shutdown-Worker-$pos") {
        override fun run() {
            while (true) {
                try {
                    queue.take().run()
                } catch (e: InterruptedException) {
                    break
                } catch (e: Throwable) {
                    //TODO replace with logger
                    e.printStackTrace()
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
            runBlocking(this) {
                moduleLoader.handleOrdered(DisableTaskRunner)
            }
        } finally {
            workerThreads.forEach { it.interrupt() }
            workerThreads.clear()
        }

    }


}