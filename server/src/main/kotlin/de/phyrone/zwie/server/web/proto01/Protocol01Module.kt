package de.phyrone.zwie.server.web.proto01

import de.phyrone.zwie.server.data.proto01.packets.PacketPing
import de.phyrone.zwie.server.data.proto01.packets.PacketPong
import de.phyrone.zwie.server.database.entity.UserEntity
import de.phyrone.zwie.server.event.UserSessionCreatedEven
import de.phyrone.zwie.server.module.CommonModule
import de.phyrone.zwie.server.module.DependsOn
import de.phyrone.zwie.server.module.EnableTaskRunner
import de.phyrone.zwie.server.module.Module
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import io.rsocket.kotlin.ConnectionAcceptor
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.RSocketRequestHandler
import io.rsocket.kotlin.ktor.server.rSocket
import io.rsocket.kotlin.payload.buildPayload
import io.rsocket.kotlin.payload.data
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.isActive
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.lang.IllegalStateException
import kotlin.system.measureNanoTime
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds

@Module("core::web::protocoll01")
@DependsOn("core::web")
@DependsOn("core::database")
@DependsOn("core::user")
@DependsOn("core::components")
class Protocol01Module : CommonModule, KoinComponent {


    private val application by inject<Application>()
    private val eventBus by inject<EventBus>()

    override suspend fun onEnable() {
        eventBus.register(this)
        application.routing {
            route("/_zwie/01/") {
                webSocket("/socket") {
                    val socket = Protocol01Socket(this, call.request.queryParameters.contains("text"))
                    val user: UserEntity
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

    @Subscribe
    @Suppress("unused")
    fun UserSessionCreatedEven.onSessionCreatedDefault() {

        runTask {
            val pongFlow = receiveFlow.asSharedFlow().mapNotNull { it as? PacketPong }
            val pingFlow = receiveFlow.asSharedFlow().mapNotNull { it as? PacketPing }
            launch {
                var sequence = 0L
                while (true) {
                    val delay = withTimeoutOrNull(10.seconds) {
                        measureNanoTime {
                            sendFlow.emit(PacketPing(sequence))
                            pongFlow.first { it.sequence == sequence }
                        }
                    }
                    //TODO remove debug message
                    println(delay?.nanoseconds ?: "timeout")
                    //TODO calucate rtt
                    delay(5.seconds)
                    sequence = sequence.inc() % Int.MAX_VALUE
                }
            }

            launch {
                pingFlow.collect { sendFlow.emit(PacketPong(it.sequence)) }
            }
        }
        runTask {
            try {
                while (true) {
                    session.user.updateLastSeen()
                    delay(30.seconds)
                }
            } finally {
                session.user.updateLastSeen()
            }
        }
    }

}