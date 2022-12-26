package de.phyrone.zwie.server.web.proto01

import de.phyrone.zwie.server.data.proto01.packets.PacketPing
import de.phyrone.zwie.server.data.proto01.packets.PacketPong
import de.phyrone.zwie.server.database.entity.UserEntity
import de.phyrone.zwie.server.event.Proto01SessionSetupEvent
import de.phyrone.zwie.server.module.CommonModule
import de.phyrone.zwie.server.module.DependsOn
import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.server.user.ZUser
import de.phyrone.zwie.server.utils.logger
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import io.rsocket.kotlin.RSocketRequestHandler
import io.rsocket.kotlin.ktor.server.rSocket
import io.rsocket.kotlin.payload.buildPayload
import io.rsocket.kotlin.payload.data
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.lang.IllegalStateException
import java.nio.ByteBuffer
import kotlin.system.measureNanoTime
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds

@Module("core::web::protocol01")
@DependsOn("core::web")
@DependsOn("core::user")
@DependsOn("core::components")
@DependsOn("core::database")
class Protocol01Module : CommonModule, KoinComponent {


    private val application by inject<Application>()
    private val eventBus by inject<EventBus>()

    override suspend fun onEnable() {
        eventBus.register(this)
        application.routing {
            route("/_zwie/01/") {
                webSocket("/socket") {
                    val socket = Protocol01Socket(this, call.request.queryParameters.contains("text"))
                    val user: ZUser
                    try {
                        user = socket.handshake()
                    } catch (e: IllegalStateException) {
                        close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, e.localizedMessage))
                        return@webSocket
                    }
                    launch { socket.runLoop(user) }.join()
                }

                route("S2S") {
                    rSocket("/not implemented yet") {
                        this.config.setupPayload
                        this.requester.cancel()
                        RSocketRequestHandler {
                            requestResponse {
                                buildPayload {
                                    data("Comming SOON")
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    override suspend fun onDisable() {
        eventBus.unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    @Suppress("unused")
    fun Proto01SessionSetupEvent.onSessionCreatedDefault() {

        runTask {

        }
    }

    companion object {
        private val logger = logger()
    }
}