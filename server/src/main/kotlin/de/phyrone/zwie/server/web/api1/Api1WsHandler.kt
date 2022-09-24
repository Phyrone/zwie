package de.phyrone.zwie.server.web.api1

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.cbor.databind.CBORMapper
import de.phyrone.zwie.server.user.UserManager
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.ProtocolUpgradeHandler
import io.vertx.ext.web.impl.Utils.canUpgradeToWebsocket
import io.vertx.kotlin.coroutines.await
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.lang.Exception
import javax.annotation.PostConstruct

@Component
class Api1WsHandler(
    val router: Router,
    dispatcher: CoroutineDispatcher,
    val userManager: UserManager,
    @Qualifier("cbor_object_mapper")
    val mapper: ObjectMapper,
) : ProtocolUpgradeHandler {

    val scope = CoroutineScope(dispatcher)

    @PostConstruct
    fun register1aWs() {
        router.route("/api/1/socket").handler(this)
    }

    override fun handle(context: RoutingContext) {
        val request = context.request()
        if (canUpgradeToWebsocket(request)) {
            scope.launch {
                try {
                    val socket = request.toWebSocket().await()
                    val userConnection = Ws1UserConnection(socket, mapper)
                    launch { userConnection.run() }.invokeOnCompletion() {
                        this.cancel()
                    }
                    userManager.handleConnection(userConnection)
                } catch (_: Exception) {
                }
            }
        } else {
            context.response().setStatusCode(400).end()
        }

    }


}