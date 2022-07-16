@file:JvmName("Main")

package de.phyrone.zwie.server

import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.sockjs.SockJSHandler

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
            println("connected -> ${sockJSSocket.writeHandlerID()}")
            sockJSSocket.handler {
                println("received ->: ${it.toString(Charsets.UTF_8)})")
            }

        })

    server.requestHandler(router)

    server.listen(4433)
}