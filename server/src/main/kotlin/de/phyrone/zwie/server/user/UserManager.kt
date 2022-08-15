package de.phyrone.zwie.server.user

import de.phyrone.zwie.server.event.EventListener
import de.phyrone.zwie.server.event.WebSetupEvent
import de.phyrone.zwie.server.utils.logger
import de.phyrone.zwie.server.network.UserConnection
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.coroutines.coroutineScope
import org.greenrobot.eventbus.Subscribe

@EventListener
object UserManager {
    private val logger = logger()

    @Subscribe
    fun WebSetupEvent.onWebSetup() {
        with(builder) {

            module {
                routing {
                    webSocket {

                    }
                }
            }
        }
    }



    class UserImpl(val connection: UserConnection) {

        suspend fun handle() = coroutineScope {

        }
    }
}