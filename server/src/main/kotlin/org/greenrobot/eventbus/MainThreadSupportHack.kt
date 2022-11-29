package org.greenrobot.eventbus

import java.util.concurrent.Executor


fun EventBusBuilder.setMainThreadExecutor(executor: MainThreadExecutor): EventBusBuilder {
    mainThreadSupportField.set(this, ExecutorMainThreadSupport(executor))
    return this
}

private val mainThreadSupportField by lazy {
    val field = EventBusBuilder::class.java.getDeclaredField("mainThreadSupport")
    field.isAccessible = true
    return@lazy field
}

private class ExecutorMainThreadSupport(
    private val executor: MainThreadExecutor,
) : MainThreadSupport {

    override fun isMainThread(): Boolean = executor.isMainThread()

    override fun createPoster(eventBus: EventBus): Poster = ExecutorPoster(executor, eventBus)
}

private class ExecutorPoster(
    private val executor: Executor,
    private val eventBus: EventBus,
) : Poster {


    override fun enqueue(subscription: Subscription, event: Any) {
        executor.execute { eventBus.invokeSubscriber(subscription, event) }
    }
}

interface MainThreadExecutor : Executor {
    fun isMainThread(): Boolean
}