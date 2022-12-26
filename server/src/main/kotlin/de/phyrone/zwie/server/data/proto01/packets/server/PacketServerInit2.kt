package de.phyrone.zwie.server.data.proto01.packets.server

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.proto01.packets.PacketServer

@JsonTypeName("server/init/init2")
data class PacketServerInit2(
    val challenge: ByteArray,
) : PacketServer
