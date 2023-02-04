package de.phyrone.zwie.shared.common.events

import kotlin.jvm.Synchronized

interface CancelableEvent : Event {
    var canceled: Boolean
}