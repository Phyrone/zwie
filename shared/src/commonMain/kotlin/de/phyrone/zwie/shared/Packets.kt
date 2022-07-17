@file:OptIn(ExperimentalJsExport::class)
@file:JsExport

package de.phyrone.zwie.shared

import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@JsExport
sealed interface ZPacket


/* --------------------------------------------------------------
 * -------------------------- Other -----------------------------
 * --------------------------------------------------------------*/

//for the beginning (proof of concept) the client will be trusted
//TODO a secure way to init and verify
@Serializable
data class PacketClientConnectionRequest(
    val uuid: String,
    val nickname: String,
) : ZPacket

@Serializable
data class PacketServerConnectionResponse(
    //null means ok, otherwise error message
    val errorMessage: String? = null,
) : ZPacket

data class PacketPing(
    val timestamp: Long,
) : ZPacket

data class PacketPong(
    val timestamp: Long,
) : ZPacket


/* --------------------------------------------------------------
 * -------------------- Both Directions -------------------------
 * --------------------------------------------------------------*/

@Serializable
data class PacketCloseConnection(
    val reason: String? = null,
) : ZPacket

@Serializable
data class PacketNotifyError(
    val errorMessage: String,
) : ZPacket


/* --------------------------------------------------------------
 * -------------------- ClientToServer --------------------------
 * --------------------------------------------------------------*/


@Serializable
data class PacketClientChannelAdd(
    val channel: String,
    val name: String,
    val type: ChannelType,
) : ZPacket

@Serializable
data class PacketClientChannelRemove(
    val channel: String,
) : ZPacket

@Serializable
data class PacketClientChannelUpdate(
    val channel: String,
    val name: String? = null,
    val type: ChannelType? = null,
) : ZPacket

@Serializable
data class PacketCLientRTCOffer(
    val channel: String,
    val sdp: String,
) : ZPacket

@Serializable
data class PacketClientChannelJoin(
    val channel: String,
) : ZPacket

@Serializable
object PacketClientChannelLeave : ZPacket

@Serializable
data class PacketClientSendChatMessage(
    val channel: String,
    val message: String,
) : ZPacket

/* --------------------------------------------------------------
 * -------------------- ServerToClient --------------------------
 * --------------------------------------------------------------*/
@Serializable
data class PacketServerEventChannelAdd(
    val id: String,
    val channel: String,
    val type: ChannelType,
) : ZPacket

@Serializable
data class PacketServerEventChannelRemove(
    val id: String,
) : ZPacket

@Serializable
data class PacketServerEventChannelUpdate(
    val channel: String,
    val name: String? = null,
    val type: ChannelType? = null,
) : ZPacket

@Serializable
data class PacketServerEventGlobalUserJoin(
    val user: String,
    val nickname: String,
) : ZPacket

@Serializable
data class PacketServerEventGlobalUserLeave(
    val user: String,
) : ZPacket

@Serializable
data class PacketServerEventChannelUserJoin(
    val channel: String,
    val user: String,
) : ZPacket

@Serializable
data class PacketServerEventChannelUserLeave(
    val channel: String,
    val user: String,
) : ZPacket

@Serializable
data class PacketServerEventChatMessage(
    val channel: String,
    val user: String?,
    val message: String,
) : ZPacket



