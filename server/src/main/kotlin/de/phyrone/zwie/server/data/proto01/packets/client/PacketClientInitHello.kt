package de.phyrone.zwie.server.data.proto01.packets.client

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.proto01.packets.PacketClient
import de.phyrone.zwie.server.data.proto01.verify.IdentityData

@JsonTypeName("client/init/hello")
class PacketClientInitHello(
    val identity: IdentityData,
) : PacketClient
