package de.phyrone.zwie.shared.common.events

import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

interface Subscriber {

    val priority: Int
    val context: CoroutineContext?
    val ignoreCancelled: Boolean
    val async: Boolean
    val clazz: KClass<out Event>

    suspend operator fun invoke(event: Event)
}