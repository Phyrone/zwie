package de.phyrone.zwie.shared

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.reflect.KClass

private val packets = arrayOf<Pair<Pair<Int, KClass<out ZPacket>>, KSerializer<out ZPacket>>>(

    /* Other */
    0x0000CC01 to PacketClientConnectionRequest::class to PacketClientConnectionRequest.serializer(),
    0x0000CC02 to PacketServerConnectionResponse::class to PacketServerConnectionResponse.serializer(),

    /* Both directions */
    0x0000EE00 to PacketCloseConnection::class to PacketCloseConnection.serializer(),
    0x0000EE01 to PacketNotifyError::class to PacketNotifyError.serializer(),

    /* Client to Server*/
    0x0000CE01 to PacketClientChannelAdd::class to PacketClientChannelAdd.serializer(),
    0x0000CE02 to PacketClientChannelRemove::class to PacketClientChannelRemove.serializer(),
    0x0000CE03 to PacketClientChannelUpdate::class to PacketClientChannelUpdate.serializer(),
    0x0000CE04 to PacketClientChannelJoin::class to PacketClientChannelJoin.serializer(),
    0x0000CE05 to PacketClientChannelLeave::class to PacketClientChannelLeave.serializer(),
    0x0000CE06 to PacketClientSendChatMessage::class to PacketClientSendChatMessage.serializer(),


    /* Server to Client */
    0x0000EC01 to PacketServerEventChannelAdd::class to PacketServerEventChannelAdd.serializer(),
    0x0000EC02 to PacketServerEventChannelRemove::class to PacketServerEventChannelRemove.serializer(),
    0x0000EC03 to PacketServerEventChannelUpdate::class to PacketServerEventChannelUpdate.serializer(),
    0x0000EC04 to PacketServerEventChannelUserJoin::class to PacketServerEventChannelUserJoin.serializer(),
    0x0000EC05 to PacketServerEventChannelUserLeave::class to PacketServerEventChannelUserLeave.serializer(),
    0x0000EC06 to PacketServerEventGlobalUserJoin::class to PacketServerEventGlobalUserJoin.serializer(),
    0x0000EC07 to PacketServerEventGlobalUserLeave::class to PacketServerEventGlobalUserLeave.serializer(),
    0x0000EC08 to PacketServerEventChatMessage::class to PacketServerEventChatMessage.serializer(),

)

@JsExport
@OptIn(ExperimentalJsExport::class)
object PacketSerializer {
    private const val PACKET_ID_PAYLOAD_SEPARATOR = ';'
    private val registeredPackets = packets
        .map { RegisteredPacket(it.first.first, it.first.second, it.second) }

    private val classToPacket = registeredPackets.associateBy { it.klass }
    private val idToPacket = registeredPackets.associateBy { it.id }

    fun serialize(packet: ZPacket): String {
        val registeredPacket = classToPacket[packet::class]
            ?: throw IllegalArgumentException("Outgoing Packet ${packet::class} is not registered")

        @Suppress("UNCHECKED_CAST")
        val encodedPayload = Json.encodeToString(registeredPacket.serializer as KSerializer<ZPacket>, packet)

        return registeredPacket.id.toString() + PACKET_ID_PAYLOAD_SEPARATOR + encodedPayload
    }

    fun deserialize(data: String): ZPacket {
        val index = data.indexOf(PACKET_ID_PAYLOAD_SEPARATOR)
        if (index == -1) throw IllegalArgumentException("Invalid packet data")
        val id = data.substring(0, index).toInt()
        val encodedPayload = data.substring(index + 1)
        val registeredPacket = idToPacket[id]
            ?: throw IllegalArgumentException("Incoming Packet with id $id is not registered")
        @Suppress("UNCHECKED_CAST")
        return Json.decodeFromString(registeredPacket.serializer as KSerializer<ZPacket>, encodedPayload)
    }
}

private class RegisteredPacket(
    val id: Int,
    val klass: KClass<out ZPacket>,
    val serializer: KSerializer<out ZPacket>,
)