package de.phyrone.zwie.server.data.proto01.packets.client

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.proto01.packets.PacketClient


@JsonTypeName("client/init/init1")
data class PacketClientInit1(
    val identity: String,
    val nickname: String,
) : PacketClient
