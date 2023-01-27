package de.phyrone.zwie.server.web.proto01a

import de.phyrone.zwie.server.module.CommonModule
import de.phyrone.zwie.server.module.DependsOn
import de.phyrone.zwie.server.module.Module
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import org.greenrobot.eventbus.EventBus
import org.koin.core.component.inject

@Module("core::web::proto01a")
@DependsOn("core::web")
@DependsOn("core::user")
@DependsOn("core::components")
@DependsOn("core::database")
class AZSocketModule:CommonModule {

    private val application by inject<Application>()
    private val eventBus by inject<EventBus>()
    override suspend fun onEnable() {
        application.routing {
            webSocket (""){

            }
        }
    }
}