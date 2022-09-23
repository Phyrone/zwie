package de.phyrone.zwie.server.web.api1

import de.phyrone.zwie.server.utils.logger
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.sockjs.SockJSHandler
import org.springframework.beans.factory.DisposableBean
import org.springframework.stereotype.Component

@Component
class Api1Components(router: Router, sockJSHandler: SockJSHandler) : DisposableBean {

    companion object {
        val logger = logger()
    }

    val handler = router.route("/api/v1/*").subRouter(sockJSHandler.socketHandler { socket ->
        logger.atInfo().log("New Connection from ${socket.remoteAddress()}")
    })

    override fun destroy() {
        handler.disable()
    }


}