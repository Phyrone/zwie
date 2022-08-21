package de.phyrone.zwie.server.event

import de.phyrone.zwie.server.user.DisconnectReason
import de.phyrone.zwie.server.user.User

data class UserLeaveEvent(
    val reason: DisconnectReason,
    val message: String,
    val user: User,
)
