package de.phyrone.zwie.server.data.packets.client

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.verify.IdentityVerification

@JsonTypeName("client/init/hello")
class PacketClientInitHello(
    val identity: IdentityVerification,
) : PacketClient