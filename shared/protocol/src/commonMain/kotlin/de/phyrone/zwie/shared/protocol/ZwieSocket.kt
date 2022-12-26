package de.phyrone.zwie.shared.protocol

import kotlinx.coroutines.flow.SharedFlow

interface ZwieSocket {

    val incomming: SharedFlow<ZProtocolPacket>
    suspend fun send(packet: ZProtocolPacket)



}