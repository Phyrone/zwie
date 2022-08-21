package de.phyrone.zwie.server.chat

import de.phyrone.zwie.server.UniqeEntity

interface ChatParticipant : ChatSender, ChatReceiver, UniqeEntity {
}