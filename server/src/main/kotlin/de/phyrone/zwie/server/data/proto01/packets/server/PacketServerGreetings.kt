package de.phyrone.zwie.server.data.proto01.packets.server

import de.phyrone.zwie.server.data.proto01.packets.PacketServer
import de.phyrone.zwie.server.data.proto01.packets.payload.PayloadChannelLayout
import de.phyrone.zwie.server.data.proto01.packets.payload.PayloadUserData

class PacketServerGreetings(
    val channelLayout: PayloadChannelLayout,
    val payloadUserData: List<PayloadUserData>,
) : PacketServer