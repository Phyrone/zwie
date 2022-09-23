package de.phyrone.zwie.server.data.chat

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("text")
data class ChatComponentText(val text: String) : ChatComponent
