package de.phyrone.zwie.server.web.proto01

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.cbor.databind.CBORMapper
import de.phyrone.zwie.server.data.proto01.packets.PacketClient
import de.phyrone.zwie.server.module.DependsOn
import de.phyrone.zwie.server.module.EnableTaskRunner
import de.phyrone.zwie.server.module.Module
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

@Module("core::web::protocoll01",)
@DependsOn("core::web")
@DependsOn("core::database")
class Protocol01Module : EnableTaskRunner, KoinComponent {


    private val jsonMapper by inject<ObjectMapper>(named("core::mapper::json"))
    private val cborMapper by inject<CBORMapper>(named("core::mapper::cbor"))

    private val application by inject<Application>()

    override suspend fun onEnable() {
        application.routing {
            route("/api/v1/") {
                webSocket("socket") { handleSocket() }
            }
        }
    }

    private suspend fun DefaultWebSocketServerSession.handleSocket() {
        launch {
            for (frame in incoming) {
                launch {
                    val packet = when (frame) {
                        is Frame.Text -> jsonMapper.readValue(frame.readText(), PacketClient::class.java)
                        is Frame.Binary -> cborMapper.readValue(frame.readBytes(), PacketClient::class.java)
                        else -> error("Unsupported Frame Type ${frame.frameType.name}")
                    }

                }
            }
        }
    }

}