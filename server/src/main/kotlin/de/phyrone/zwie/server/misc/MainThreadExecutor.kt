package de.phyrone.zwie.server.misc

import de.phyrone.zwie.server.event.MainThreadStartedEvent
import de.phyrone.zwie.server.utils.logger
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.Runnable
import org.greenrobot.eventbus.EventBus
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue
import kotlin.coroutines.CoroutineContext
import org.greenrobot.eventbus.MainThreadExecutor as EventBusMainThreadExecutor

class MainThreadExecutor : Executor, MainCoroutineDispatcher(), EventBusMainThreadExecutor, KoinComponent {
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

    override fun isMainThread(): Boolean {
        return if (mainThread == null) true else Thread.currentThread() == mainThread
    }

    @Synchronized
    fun runLoop(): Nothing {
        mainThread = Thread.currentThread()
        eventBus.postSticky(MainThreadStartedEvent)
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