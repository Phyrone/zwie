package de.phyrone.zwie.server.event

import de.phyrone.zwie.server.chat.AbstractChatMessage
import de.phyrone.zwie.server.user.User

data class UserPreChatMessageSendEvent(
    var user: User,
    var message: AbstractChatMessage,
) : CancelableEvent {
    override var isCancelled: Boolean = false
}