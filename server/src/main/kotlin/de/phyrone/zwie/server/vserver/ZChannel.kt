package de.phyrone.zwie.server.vserver

import kotlinx.coroutines.flow.MutableStateFlow

interface ZChannel {
    val id: Long
    val name: MutableStateFlow<String>
    val voiceSessions: MutableStateFlow<Set<ZUserSession>>
}