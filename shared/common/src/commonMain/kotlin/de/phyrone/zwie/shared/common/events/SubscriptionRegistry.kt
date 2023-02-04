package de.phyrone.zwie.shared.common.events

import kotlin.reflect.KClass

expect class SubscriptionRegistry {

    operator fun get(event: Event): List<Subscriber>

    operator fun contains(subscriber: Subscriber): Boolean

    operator fun plus(subscriber: Subscriber): SubscriptionRegistry


    operator fun minus(subscriber: Subscriber): SubscriptionRegistry

    companion object {
        fun empty(): SubscriptionRegistry
    }
}