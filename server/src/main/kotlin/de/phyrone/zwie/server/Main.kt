@file:JvmName("Main")

package de.phyrone.zwie.server

import de.phyrone.zwie.server.instance.ServerInstance
import dev.onvoid.webrtc.PeerConnectionFactory
import dev.onvoid.webrtc.PeerConnectionObserver
import dev.onvoid.webrtc.RTCConfiguration
import dev.onvoid.webrtc.RTCIceServer
import dev.onvoid.webrtc.RTCIceTransportPolicy
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.Dispatchers
import org.greenrobot.eventbus.EventBus
import org.koin.core.context.startKoin
import org.koin.dsl.module
import java.util.UUID
import java.util.concurrent.Executors


val logger = logger()
fun main(args: Array<String>) {


    logger.atInfo().log("Starting Zwie Server...")
    val server = ServerInstance(Dispatchers.Default)
    server.start()
    /*
    startKoin {
        modules(module() {
            single { Executors.newCachedThreadPool() }
            single {
                EventBus.builder()
                    .eventInheritance(true)
                    .sendNoSubscriberEvent(true)
                    .sendSubscriberExceptionEvent(true)

                    .logSubscriberExceptions(true)
                    .logSubscriberExceptions(true)

                    .executorService(get())
                    .installDefaultEventBus()
            }
        })

    }


    val r = PeerConnectionFactory().createPeerConnection(
        RTCConfiguration().also { rtcConfiguration ->
            rtcConfiguration.iceTransportPolicy = RTCIceTransportPolicy.RELAY
            rtcConfiguration.iceServers = listOf(
                RTCIceServer().also { }
            )
            rtcConfiguration;
        }, PeerConnectionObserver { candidate ->
            candidate;
        }
    )
    embeddedServer(Netty) {
        routing {
            webSocket {

               val frame =  incoming.receive()

                if(frame is Frame.Text){

                }
            }
        }
    }
     */
}