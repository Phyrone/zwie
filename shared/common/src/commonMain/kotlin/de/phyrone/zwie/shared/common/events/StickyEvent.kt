package de.phyrone.zwie.shared.common.events

interface StickyEvent : Event {
    val sticky: Boolean
        get() = true
}