package de.phyrone.zwie.shared.common.events

class EventDeliveryException(
    val event: Event,
    val subscriber: Subscriber,
    val error: Throwable
) : Exception("Error while delivering event $event to $subscriber", error)