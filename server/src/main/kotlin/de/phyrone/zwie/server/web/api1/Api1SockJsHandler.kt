package de.phyrone.zwie.server.web.api1

import com.fasterxml.jackson.databind.ObjectMapper
import de.phyrone.zwie.server.user.UserManager
import de.phyrone.zwie.server.utils.logger
import io.vertx.core.http.HttpServer
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.sockjs.SockJSHandler
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class Api1SockJsHandler(
    @Qualifier("json_object_mapper")
    val objectMapper: ObjectMapper,
    router: Router,
    sockJSHandler: SockJSHandler,
    coroutineDispatcher: CoroutineDispatcher,
    val userManager: UserManager,
    val server: HttpServer,
) : DisposableBean {

    val scope = CoroutineScope(coroutineDispatcher)

    companion object {
        val logger = logger()
    }

    val handlerjson = router.route("/api/1/socket/*").subRouter(sockJSHandler.socketHandler { socket ->
        logger.atInfo().log("New Connection from ${socket.remoteAddress()}")
        scope.launch {
            try {
                val userConnection = SockJs1UserConnection(socket, objectMapper)
                launch { userConnection.run() }.invokeOnCompletion() {
                    this.cancel()
                }
                userManager.handleConnection(userConnection)
            } catch (_: CancellationException) {
            }
        }
    })

    override fun destroy() {
        handlerjson.disable()
    }


}