package de.phyrone.zwie.shared.protocol.core

import de.phyrone.zwie.shared.protocol.PacketCoder
import de.phyrone.zwie.shared.protocol.core.channel.ChannelCoder
import de.phyrone.zwie.shared.protocol.core.control.ControlPacketCoder
import de.phyrone.zwie.shared.protocol.core.ping.PingCoder
import de.phyrone.zwie.shared.protocol.core.user.UserCoder

val coreCodecs = listOf<Pair<UInt, PacketCoder<*>>>(
    0x00u to PingCoder,
    //0x01u to ExtensionCoder,
    //0x02u to SystemPacketCoder,
    0x03u to UserCoder,
    0x04u to ChannelCoder,


    0xFFu to ControlPacketCoder,
).map { (id, coder) -> id.toUByte() to coder }

val idToCodec = coreCodecs.associateBy { it.first }
val codecToId = coreCodecs.associateBy { it.second }