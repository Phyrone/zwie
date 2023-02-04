package de.phyrone.zwie.shared.common.events

import com.sksamuel.aedile.core.caffeineBuilder
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge

actual class SubscriptionRegistry private constructor(
    private val subscribers: Set<Subscriber>,
    private val clazzToSubscriber: Map<Class<*>, List<Subscriber>>,
) {

    actual operator fun get(event: Event): List<Subscriber> {
        val clazz = event::class.java
        return (clazz.classes + clazz).flatMap { clazzToSubscriber[it] ?: emptyList() }
            .filter { it.clazz.isInstance(event) }
    }

    actual operator fun plus(subscriber: Subscriber): SubscriptionRegistry {
        val clazz = subscriber.clazz.java
        if (subscribers.contains(subscriber))
            return this
        val newSubscribers = subscribers + subscriber
        val newMap = clazzToSubscriber + (clazz to (clazzToSubscriber.getOrDefault(clazz, emptyList()) + subscriber))
        return SubscriptionRegistry(newSubscribers, newMap)
    }

    actual operator fun minus(subscriber: Subscriber): SubscriptionRegistry {
        val clazz = subscriber.clazz.java
        if (!subscribers.contains(subscriber))
            return this

        val newSubscribers = subscribers - subscriber
        val newMap = clazzToSubscriber + (clazz to (clazzToSubscriber.getOrDefault(clazz, emptyList()) - subscriber))
        //TODO remove empty lists
        return SubscriptionRegistry(newSubscribers, newMap)
    }

    actual companion object {
        actual fun empty(): SubscriptionRegistry = SubscriptionRegistry(emptySet(), emptyMap())
    }

    actual operator fun contains(subscriber: Subscriber): Boolean = subscribers.contains(subscriber)


}