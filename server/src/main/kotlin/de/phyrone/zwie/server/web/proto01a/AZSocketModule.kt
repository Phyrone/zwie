package de.phyrone.zwie.server.web.proto01a

import de.phyrone.zwie.server.module.CommonModule
import de.phyrone.zwie.server.module.DependsOn
import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.shared.crypt.gpg.GPG
import de.phyrone.zwie.shared.protocol.crypt.AsymPacketCrypt
import de.phyrone.zwie.shared.protocol.crypt.SignOnlyPacketCrypt
import de.phyrone.zwie.shared.protocol.intl.az.ServerHandshakeResult
import de.phyrone.zwie.shared.protocol.intl.az.runHandshakeServer
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.time.withTimeout
import kotlinx.coroutines.withTimeout
import org.bouncycastle.util.encoders.Hex
import org.greenrobot.eventbus.EventBus
import org.koin.core.component.inject
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@Module("core::web::proto01a")
@DependsOn("core::web")
//@DependsOn("core::user")
@DependsOn("core::components")
@DependsOn("core::database")
class AZSocketModule : CommonModule {

    private val application by inject<Application>()
    override suspend fun onEnable() {
        application.routing {
            webSocket("/_zw_/proto01a") {
                println("Opening Connection: ${this.call.request.origin.remoteHost}:${this.call.request.origin.remotePort}")
                val serverKey = GPG.generateKey()
                val (_, clientKey, alreadySecure) = withTimeout(10.seconds) {
                    runHandshakeServer(serverKey)
                }
                println("Connected: ${Hex.toHexString(clientKey.pgpPublicKeyRing.publicKey.fingerprint)}")

                val packetCrypt = if (alreadySecure) SignOnlyPacketCrypt(serverKey, clientKey)
                else AsymPacketCrypt(serverKey, clientKey)
                val socket = AzServerSocket(this, packetCrypt)
                try {
                    delay(10.minutes)
                } finally {
                    socket.close()
                    println("Disconnected: ${Hex.toHexString(clientKey.pgpPublicKeyRing.publicKey.fingerprint)}")
                }
            }
        }
    }
}