package de.phyrone.zwie.server.user

import de.phyrone.zwie.server.UniqeEntity
import java.time.LocalDateTime

interface OfflineUser : UniqeEntity {


    suspend fun ban(reason: String? = null, until: LocalDateTime? = null)

    suspend fun unban()
}