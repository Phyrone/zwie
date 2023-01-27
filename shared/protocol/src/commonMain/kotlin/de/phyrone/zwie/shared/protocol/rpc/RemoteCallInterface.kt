package de.phyrone.zwie.shared.protocol.rpc

import de.phyrone.zwie.shared.protocol.coder.PacketDecoder
import de.phyrone.zwie.shared.protocol.coder.PacketEncoder
import kotlinx.coroutines.flow.Flow
import kotlin.properties.ReadOnlyProperty

interface RemoteCallInterface {


    suspend fun <Req> remoteEvent(
        name: String,
        requestEncoder: PacketEncoder<Req>
    ): RemoteCall<Req, Unit>

    suspend fun <Req, Res> remoteCall(
        name: String,
        requestEncoder: PacketEncoder<Req>,
        responseDecoder: PacketDecoder<Res>
    ): RemoteCall<Req, Res>

    suspend fun <Req, Res> remoteStreamCall(
        name: String,
        requestEncoder: PacketEncoder<Req>,
        responseDecoder: PacketDecoder<Res>
    ): RemoteStreamCall<Req, Res>

    suspend fun <Req, ChannelIN, ChannelOUT> remoteChannelCall(
        name: String,
        requestEncoder: PacketEncoder<Req>,
        channelInDecoder: PacketDecoder<ChannelIN>,
        channelOutEncoder: PacketEncoder<ChannelOUT>
    ): RemoteChannelCall<Req, ChannelIN, ChannelOUT>




}