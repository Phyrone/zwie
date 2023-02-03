package de.phyrone.zwie.shared.common

import kotlin.reflect.full.findAnnotation


fun EventBus.subscribeAll(
    instance: Any,
) {
    val members = instance::class.members
        .mapNotNull { member ->
            member.findAnnotation<Annotation>()?.let { annotation -> member to annotation }
        }
    members.forEach { (member, annotation) ->
        
    }


}