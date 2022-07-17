@file:JvmName("Main")

package de.phyrone.zwie.server

import de.phyrone.zwie.shared.PacketServerEventChannelUserJoin
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.sockjs.SockJSHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main(args: Array<String>) {
    val v = Vertx.vertx()
    val server = v.createHttpServer()

    val sockeJS = SockJSHandler.create(v)
    val router = Router.router(v)

    router.route("/").handler {
        it.response().end("Hello World");
    }
    router.route("/socket/*")
        .subRouter(sockeJS.socketHandler { sockJSSocket ->
            println("connected -> ${sockJSSocket.remoteAddress()}")
            val wrapper = SocketJsConnectionWrapper(sockJSSocket)
            GlobalScope.launch {
                var counter = 0
                var counter2 = Int.MAX_VALUE
                while (true) {
                    wrapper.send(PacketServerEventChannelUserJoin("CC:${counter2--}", "UU:${counter++}"))
                    delay(1000)
                }
            }


        })

    server.requestHandler(router)

    server.listen(4433)
}