package de.phyrone.zwie.server.event

import de.phyrone.zwie.shared.common.events.StickyEvent

object MainThreadStartedEvent : StickyEvent {
    override val sticky: Boolean = true
}