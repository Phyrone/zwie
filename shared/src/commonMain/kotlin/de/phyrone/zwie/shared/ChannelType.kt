package de.phyrone.zwie.shared

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
enum class ChannelType {
    VOICE, TEXT, DUMMY
}