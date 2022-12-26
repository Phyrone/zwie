package de.phyrone.zwie.server.data.chat

import com.fasterxml.jackson.annotation.JsonTypeInfo
import de.phyrone.zwie.server.utils.JsonComponent

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_ARRAY)
sealed interface ChatComponent : JsonComponent {
    companion object {
        fun fromText(text: String): ChatComponent = ChatComponentText(text)
    }
}