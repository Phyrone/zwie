package de.phyrone.zwie.shared

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
data class PacketHeader(
    val type: Any,
)