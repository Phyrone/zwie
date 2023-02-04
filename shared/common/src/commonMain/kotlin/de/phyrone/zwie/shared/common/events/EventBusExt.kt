package de.phyrone.zwie.shared.common.events

import kotlin.coroutines.CoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.reflect.KClass


@PublishedApi
internal class FunctionalSubscriber<T : Event>(
    override val priority: Int,
    override val context: CoroutineContext?,
    override val ignoreCancelled: Boolean,
    override val async: Boolean,
    override val clazz: KClass<T>,
    val function: suspend (T) -> Unit
) : Subscriber {
    override suspend fun invoke(event: Event) {
        function(event as T)
    }
}

@JvmOverloads
inline fun <reified T : Event> EventBus.subscribe(
    clazz: KClass<T> = T::class,
    priority: Int = 0,
    context: CoroutineContext? = null,
    ignoreCancelled: Boolean = false,
    async: Boolean = false,
    noinline function: suspend (T) -> Unit
): Unsubriber {
    val subscriber = FunctionalSubscriber(priority, context, ignoreCancelled, async, clazz, function)
    addSubscriber(subscriber)
    return object : Unsubriber {
        override fun invoke() {
            removeSubscriber(subscriber)
        }
    }
}
