package de.phyrone.zwie.shared.common

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.reflect.KClass
import kotlin.reflect.cast

class EventBus(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    @PublishedApi
    internal val eventFlow = MutableSharedFlow<Any>()

    @PublishedApi
    internal val scope = CoroutineScope(dispatcher)

    suspend fun post(event: Any) {
        eventFlow.emit(event)
    }

    suspend infix fun <T : Any> await(event: KClass<T>): T = event.cast(eventFlow.first { it::class == event })


    fun <T : Any> register(kClass: KClass<T>, handler: suspend (T) -> Unit): Registration {
        val job = scope.launch {
            @Suppress("UNCHECKED_CAST")
            (eventFlow.filter { kClass.isInstance(it) } as Flow<T>).collect(handler)
        }
        return RegistrationImpl(job)
    }

    inline fun <reified T : Any> register(noinline handler: suspend (T) -> Unit): Registration {
        val job = scope.launch {
            eventFlow.filterIsInstance<T>().collect(handler)
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

    interface Registration {
        fun unregister()
    }

    fun close() {
        scope.cancel()
    }
}