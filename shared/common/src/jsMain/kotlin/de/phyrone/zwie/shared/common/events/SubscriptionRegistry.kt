package de.phyrone.zwie.shared.common.events

actual class SubscriptionRegistry(
    private val subscribers: Set<Subscriber>
) {
    actual companion object {
        actual fun empty(): SubscriptionRegistry = SubscriptionRegistry(emptySet())
    }

    actual operator fun get(event: Event): List<Subscriber> = subscribers.filter { it.clazz.isInstance(event) }

    actual operator fun contains(subscriber: Subscriber): Boolean = subscribers.contains(subscriber)

    actual operator fun plus(subscriber: Subscriber): SubscriptionRegistry {
        if(subscriber in subscribers) return this
        return SubscriptionRegistry(subscribers + subscriber)
    }

    actual operator fun minus(subscriber: Subscriber): SubscriptionRegistry {
        if(subscriber !in subscribers) return this
        return SubscriptionRegistry(subscribers - subscriber)
    }


}