package de.phyrone.zwie.server.user

import de.phyrone.zwie.server.data.packets.client.PacketClientInitHello
import de.phyrone.zwie.server.utils.logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withTimeout
import org.springframework.beans.factory.DisposableBean
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class UserManager(
    val publisher: ApplicationEventPublisher,
    dispatcher: CoroutineDispatcher,
) : DisposableBean {

    private val scope = CoroutineScope(dispatcher)

    companion object {
        private val logger = logger()
    }


    suspend fun handleConnection(userConnection: UserConnection) = coroutineScope {
        val request1 = withTimeout(3000) { userConnection.receiveChannel.receive() as PacketClientInitHello }
        request1
    }

    override fun destroy() {
        scope.cancel()
    }


}