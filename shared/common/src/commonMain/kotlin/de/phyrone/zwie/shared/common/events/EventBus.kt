package de.phyrone.zwie.shared.common.events

import kotlinx.coroutines.flow.SharedFlow
import kotlin.reflect.KClass


interface EventBus {


    val preEventFlow: SharedFlow<Event>
    val postEventFlow: SharedFlow<Event>

    suspend fun post(event: Event)

    fun removeStickyEvent(event: StickyEvent)

    suspend infix fun <T : Event> await(event: KClass<T>): T


    fun addSubscriber(subscriber: Subscriber)

    fun removeSubscriber(subscriber: Subscriber)

    fun containsSubscriber(subscriber: Subscriber): Boolean

    companion object {
        operator fun invoke(): EventBus = EventBusImpl()
    }

}