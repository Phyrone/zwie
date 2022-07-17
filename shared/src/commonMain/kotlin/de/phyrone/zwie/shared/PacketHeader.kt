package de.phyrone.zwie.shared

import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
data class PacketHeader(
    val type: Any,
)

fun a() {
    val b = ProtoBuf.encodeToByteArray(PacketServerConnectionResponse())

}