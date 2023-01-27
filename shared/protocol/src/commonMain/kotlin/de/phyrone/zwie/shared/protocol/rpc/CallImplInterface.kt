package de.phyrone.zwie.shared.protocol.rpc

import de.phyrone.zwie.shared.protocol.coder.PacketDecoder
import de.phyrone.zwie.shared.protocol.coder.PacketEncoder
import kotlinx.coroutines.flow.Flow
import kotlin.properties.ReadOnlyProperty

interface CallImplInterface {

    suspend fun <Req> eventImpl(
        name: String,
        requestDecoder: PacketDecoder<Req>,
        impl: suspend (Req) -> Unit
    )

    suspend fun <Req, Res> callImpl(
        name: String,
        requestDecoder: PacketDecoder<Req>,
        responseEncoder: PacketEncoder<Res>,
        impl: suspend (Req) -> Res
    )

    suspend fun <Req, Res> streamCallImpl(
        name: String,
        requestDecoder: PacketDecoder<Req>,
        responseEncoder: PacketEncoder<Res>,
        impl: suspend (Req) -> Flow<Res>
    )

    suspend fun <Req, ChannelIN, ChannelOUT> channelCallImpl(
        name: String,
        requestDecoder: PacketDecoder<Req>,
        channelInDecoder: PacketDecoder<ChannelIN>,
        channelOutEncoder: PacketEncoder<ChannelOUT>,
        impl: suspend ZChannel<ChannelIN, ChannelOUT>.(Req) -> Unit
    )


}