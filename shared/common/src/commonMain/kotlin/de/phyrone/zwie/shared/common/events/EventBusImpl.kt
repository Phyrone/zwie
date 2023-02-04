package de.phyrone.zwie.shared.common.events

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.reflect.KClass
import kotlin.time.ExperimentalTime

internal class EventBusImpl(
    private val stickyEventContext: CoroutineContext = Dispatchers.Default,
) : EventBus {


    private val registryAndStickyEvents = MutableStateFlow(SubscriptionRegistry.empty() to emptyList<Event>())
    override val preEventFlow: MutableSharedFlow<Event> = MutableSharedFlow(0, Int.MAX_VALUE, BufferOverflow.SUSPEND)
    override val postEventFlow: MutableSharedFlow<Event> = MutableSharedFlow(0, Int.MAX_VALUE, BufferOverflow.SUSPEND)


    @OptIn(ExperimentalTime::class)
    override suspend fun post(event: Event) {
        preEventFlow.emit(event)
        try {
            EventDeliveryImpl(event, registryAndStickyEvents.value.first)()
        } finally {
            postEventFlow.emit(event)
        }
        registryAndStickyEvents.update { (registry, stickyEvents) -> registry to (stickyEvents + event) }
    }

    private inner class EventDeliveryImpl(
        val event: Event,
        val registry: SubscriptionRegistry,
    ) : EventDelivery {
        val subscribers = registry[event]
        val grouped = subscribers.groupBy { it.context }
            .map { (dispatcher, subscribers) -> dispatcher to subscribers.sortedBy { it.priority } }

        suspend operator fun invoke() {
            coroutineScope eventDeliveryScope@{
                grouped.map { (dispatcher, subscribers) ->
                    launch(context = dispatcher ?: EmptyCoroutineContext) {
                        val subTasks = mutableSetOf<Job>()
                        subscribers.forEach { subscriber ->
                            runCatching {
                                val cancelled = (event as? CancelableEvent)?.canceled ?: false
                                if (cancelled && !subscriber.ignoreCancelled) return@forEach
                                if (subscriber.async) subTasks.add(launch { subscriber.invoke(event) })
                                else subscriber.invoke(event)
                            }.onFailure { error ->
                                if (event is UncatchingEvent) {
                                    val eventDeliveryException = EventDeliveryException(event, subscriber, error)
                                    this@eventDeliveryScope.cancel("EventDeliveryException", eventDeliveryException)
                                    throw eventDeliveryException
                                } else if (event !is DeliveryErrorEvent) {
                                    subTasks.add(launch { post(DeliveryErrorEvent(event, subscriber, error)) })
                                }
                            }
                        }
                        subTasks.joinAll()
                    }
                }.joinAll()
            }
        }
    }


    override suspend fun <T : Event> await(event: KClass<T>): T =
        preEventFlow.first { event.isInstance(it) }.let { eventInstance -> eventInstance as T }


    override fun addSubscriber(subscriber: Subscriber) {
        val (_, stickyEvents) = registryAndStickyEvents.updateAndGet { (it.first + subscriber) to it.second }
        CoroutineScope(stickyEventContext).launch {
            stickyEvents.forEach { event ->
                if (subscriber.clazz.isInstance(event)
                    && ((event as? CancelableEvent)?.canceled == false || !subscriber.ignoreCancelled)

                ) runCatching { subscriber.invoke(event) }
            }
        }
    }

    override fun removeStickyEvent(event: StickyEvent) {
        registryAndStickyEvents.update { subscriptionRegistryListPair -> subscriptionRegistryListPair.first to subscriptionRegistryListPair.second.filter { it != event } }
    }

    override fun removeSubscriber(subscriber: Subscriber) {
        registryAndStickyEvents.update { (it.first - subscriber) to it.second }
    }

    override fun containsSubscriber(subscriber: Subscriber): Boolean = subscriber in registryAndStickyEvents.value.first


}