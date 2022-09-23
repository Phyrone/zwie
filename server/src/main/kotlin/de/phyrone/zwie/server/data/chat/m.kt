package de.phyrone.zwie.server.data.chat

import com.fasterxml.jackson.databind.ObjectMapper
import de.phyrone.zwie.server.data.packets.client.PacketClientChatMessage

fun main() {
    val mapper = ObjectMapper().findAndRegisterModules()
    println(
        mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
            PacketClientChatMessage(
                listOf(
                    ChatComponentText("hi everyone"),
                    ChatComponentSpace,
                    ChatComponentText("i am a test"),
                )
            )
        )
    )
}