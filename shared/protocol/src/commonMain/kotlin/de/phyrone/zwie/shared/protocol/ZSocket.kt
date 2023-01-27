package de.phyrone.zwie.shared.protocol

import de.phyrone.zwie.shared.protocol.coder.PacketDecoder
import de.phyrone.zwie.shared.protocol.coder.PacketEncoder
import de.phyrone.zwie.shared.protocol.rpc.ZChannel
import kotlin.time.Duration

interface ZSocket /* : CallInterface TODO */ {

    suspend fun ping(): Duration

    suspend fun ready(iteration: UInt = 1u)

    //suspend fun namespace(name: String): ZNamespace
    //suspend fun <T> service(factory: ServiceFactory<T>): T

    suspend fun <In, Out> openChannel(
        name: String,
        incomming: PacketDecoder<In>,
        outgoint: PacketEncoder<Out>
    ): ZChannel<In, Out>


    suspend fun close()



}