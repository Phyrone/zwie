package de.phyrone.musicnova.misc

import de.phyrone.zwie.server.utils.logger
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.Runnable
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue
import kotlin.coroutines.CoroutineContext

class MainThreadExecutor : Executor, MainCoroutineDispatcher() {

    private val logger = logger()
    private val queue = LinkedBlockingQueue<Runnable>()

    init {
        queue.add(Runnable { logger.atFine().log("Started...") })
    }

    override fun execute(command: Runnable) {
        queue.add(command)
    }

    override val immediate: MainCoroutineDispatcher = this

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        queue.add(block)
    }

    var thread: Thread? = null
        private set

    @Synchronized
    fun runLoop(): Nothing {
        thread = Thread.currentThread()
        while (true) {
            val command = queue.take()
            try {
                command.run()
            } catch (e: InterruptedException) {
                throw e
            } catch (e: Exception) {
                logger.atSevere().withCause(e).log("Uncaught Exception in MainThreadExecutor")
            }
        }
    }

    companion object {
        private val logger = logger()
    }

}