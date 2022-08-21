package de.phyrone.zwie.server.event

import de.phyrone.zwie.server.user.User

data class UserJoinEvent(
    val user: User,
)
