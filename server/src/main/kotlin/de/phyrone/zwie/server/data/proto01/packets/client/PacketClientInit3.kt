package de.phyrone.zwie.server.data.proto01.packets.client

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.proto01.packets.PacketClient

@JsonTypeName("client/init/init3")
data class PacketClientInit3(
    val response: String,
):PacketClient
