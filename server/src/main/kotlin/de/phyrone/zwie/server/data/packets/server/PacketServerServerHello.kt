package de.phyrone.zwie.server.data.packets.server

import com.fasterxml.jackson.annotation.JsonTypeName
import java.util.UUID

@JsonTypeName("server/init/hello")
class PacketServerServerHello(
    val uuid: UUID,
) : PacketServer