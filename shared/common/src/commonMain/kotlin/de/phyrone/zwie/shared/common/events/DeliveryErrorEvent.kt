package de.phyrone.zwie.shared.common.events

data class DeliveryErrorEvent internal constructor(
    val event: Event,
    val subscriber: Subscriber,
    val error: Throwable
) : Event