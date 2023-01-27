package de.phyrone.zwie.shared.protocol.rpc

import io.ktor.utils.io.core.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.SharedFlow

interface ZChannel<ChannelIN, ChannelOUT> : ReceiveChannel<ChannelIN>, SendChannel<ChannelOUT>, Closeable {
    val incomming: ReceiveChannel<ChannelIN>
    val outgoing: SendChannel<ChannelOUT>

    operator fun component1() = incomming
    operator fun component2() = outgoing
}