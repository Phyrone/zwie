package de.phyrone.zwie.server.data.proto01.packets.server

import com.fasterxml.jackson.annotation.JsonTypeName
import de.phyrone.zwie.server.data.proto01.packets.PacketServer
import de.phyrone.zwie.server.data.proto01.packets.payload.PayloadChannelLayout
import de.phyrone.zwie.server.data.proto01.packets.payload.PayloadUserData

@JsonTypeName("server/channel/updatelayout")
class PacketServerUpdateChannelLayout(
    val channelLayout: PayloadChannelLayout,
) : PacketServer