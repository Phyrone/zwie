package de.phyrone.zwie.shared.common.events

import kotlin.reflect.full.findAnnotation

fun EventBus.subscribeAll(instance: Any) {
    val candidates = instance::class.members
        .mapNotNull { member -> member.findAnnotation<Subscribe>()?.let { member to it } }
}