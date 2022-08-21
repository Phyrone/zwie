package de.phyrone.zwie.server.chat

data class SimpleTextChatMessage(
    val text: String,
    override val sender: ChatSender,
) : AbstractChatMessage