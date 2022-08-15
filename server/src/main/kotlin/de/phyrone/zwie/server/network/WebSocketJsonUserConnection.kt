package de.phyrone.zwie.server.network

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.server.websocket.*
import io.ktor.websocket.*

class WebSocketJsonUserConnection(val socket: DefaultWebSocketServerSession) : UserConnection {

    private val incoming = socket.incoming
    private val mapper = ObjectMapper()
    private val classToIdRegistry = mutableMapOf<Class<*>, String>()
    private val idToClassRegistry = mutableMapOf<String, Class<*>>()

    override suspend fun register(id: String, clazz: Class<*>) {
        idToClassRegistry[id] = clazz
        classToIdRegistry[clazz] = id
    }

    override suspend fun unregister(id: String) {
        val clazz = idToClassRegistry.remove(id)
        classToIdRegistry.remove(clazz)
    }

    override suspend fun send(message: Any) {
        val id = classToIdRegistry[message::class.java]
            ?: throw IllegalArgumentException("outgoing packet with class ${message::class.java} is not registered")
        val outgoingPacket = LowTextOutgoingPacket(id, message)
        socket.send(mapper.writeValueAsString(outgoingPacket))
    }

    override suspend fun receive(): Any {
        val frame = incoming.receive()
        if (frame is Frame.Text) {
            val (id, payload) = mapper.readValue(frame.readText(), LowTextIncommingPacket::class.java)
            val clazz = idToClassRegistry[id]
                ?: throw IllegalArgumentException("incoming packet with id $id is not registered")

            return mapper.readValue(payload, clazz)

        } else {
            throw IllegalArgumentException("incoming frame type must be text but was ${frame.frameType.name}")
        }
    }

}