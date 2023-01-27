package de.phyrone.zwie.server.web.proto1a

import de.phyrone.zwie.server.module.CommonModule
import de.phyrone.zwie.shared.protocol.crypt.NoOpPacketCrypt
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.rsocket.kotlin.ktor.server.rSocket
import org.koin.core.component.inject

class Proto1a : CommonModule {


    private val application by inject<Application>()
    override suspend fun onEnable() {
        application.routing {

            rSocket("_zwie/1a") {
                val zSocket = ZSocketImpl(this.requester, NoOpPacketCrypt)
                //TODO: new connection event
                return@rSocket zSocket.requestHandler
            }
        }
    }
}