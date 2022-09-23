package de.phyrone.zwie.server.data.packets

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.packets.PacketInOut

@JsonTypeName("pong")
class PacketInOutPong : PacketInOut