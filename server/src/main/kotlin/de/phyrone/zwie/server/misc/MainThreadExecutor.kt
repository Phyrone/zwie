package de.phyrone.zwie.server.misc

import de.phyrone.zwie.server.event.MainThreadStartedEvent
import de.phyrone.zwie.server.utils.logger
import de.phyrone.zwie.shared.common.events.EventBus
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue
import kotlin.coroutines.CoroutineContext

class MainThreadExecutor : Executor, MainCoroutineDispatcher(), KoinComponent {
    private val queue = LinkedBlockingQueue<Runnable>()
    private val eventBus by inject<EventBus>()

    /*
    init {
        queue.add(Runnable { logger.atFine().log("Started...") })
    }
    */


    override fun execute(command: Runnable) {
        queue.add(command)
    }

    override val immediate: MainCoroutineDispatcher = this

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        queue.add(block)
    }

    var mainThread: Thread? = null
        private set

    @Synchronized
    fun runLoop(): Nothing {
        mainThread = Thread.currentThread()
        runBlocking { eventBus.post(MainThreadStartedEvent) }
        while (true) {
            val command = queue.take()
            try {
                command.run()
            } catch (e: InterruptedException) {
                throw e
            } catch (e: Throwable) {
                logger.atSevere().withCause(e).log("Uncaught Exception in MainThreadExecutor")
            }
        }
    }

    companion object {
        private val logger = logger()
    }

}