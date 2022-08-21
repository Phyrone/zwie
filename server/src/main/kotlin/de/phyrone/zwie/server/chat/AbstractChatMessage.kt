package de.phyrone.zwie.server.chat

import de.phyrone.zwie.server.user.User

interface AbstractChatMessage {
    val sender: ChatSender
}