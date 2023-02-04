package de.phyrone.zwie.shared.common

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlin.jvm.JvmOverloads
import kotlin.reflect.KClass
import kotlin.reflect.cast


class LegacyEventBus @JvmOverloads constructor(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {


    private val stickyFlow = MutableSharedFlow<Any>(Int.MAX_VALUE, Int.MAX_VALUE, BufferOverflow.SUSPEND)
    private val liveFlow = MutableSharedFlow<Any>(0, Int.MAX_VALUE, BufferOverflow.SUSPEND)

    @PublishedApi
    internal val mergedEventFlow = merge(stickyFlow, liveFlow)

    @PublishedApi
    internal val scope = CoroutineScope(dispatcher)


    @JvmOverloads
    suspend fun post(event: Any, sticky: Boolean = false) {
        if (sticky)
            stickyFlow.emit(event)
        else
            liveFlow.emit(event)
    }

    suspend infix fun <T : Any> await(event: KClass<T>): T = event.cast(mergedEventFlow.first { event.isInstance(it) })

    fun <T : Any> subscribe(kClass: KClass<T>, handler: suspend (T) -> Unit): Registration {
        val job = scope.launch {
            @Suppress("UNCHECKED_CAST")
            (mergedEventFlow.filter { kClass.isInstance(it) } as Flow<T>).collect(handler)
        }
        return RegistrationImpl(job)
    }

    inline fun <reified T : Any> subscribe(noinline handler: suspend (T) -> Unit): Registration {
        val job = scope.launch {
            mergedEventFlow.filterIsInstance<T>().collect(handler)
        }
        return RegistrationImpl(job)
    }


    @PublishedApi
    internal data class RegistrationImpl(
        val job: Job,
    ) : Registration {
        override fun unregister() {
            job.cancel()
        }
    }

    fun clearStickyEvents() {
        stickyFlow.resetReplayCache()
    }

    interface Registration {
        fun unregister()
    }

    fun close() {
        scope.cancel()
    }
}