package de.phyrone.zwie.server

import de.phyrone.zwie.server.event.EventListener
import de.phyrone.zwie.server.event.KoinStartupDoneEvent
import de.phyrone.zwie.server.event.WebSetupEvent
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.CoroutineDispatcher
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.bind
import org.koin.dsl.module
import java.security.KeyStore


@EventListener
object WebServerHandler : KoinComponent {

    private val server by inject<ApplicationEngine>()

    @Subscribe
    fun KoinStartupDoneEvent.registerComponents() {
        koinApplication.modules(module {
            single {
                embeddedServer(Netty, get<ApplicationEngineEnvironment>()) {
                    this.shareWorkGroup = true
                    this.tcpKeepAlive = true
                }
            } bind ApplicationEngine::class
            single {
                applicationEngineEnvironment {
                    parentCoroutineContext = get<CoroutineDispatcher>()
                    connector {
                        port = 8080
                    }
                    get<EventBus>().post(WebSetupEvent(this))
                }
            }
            single { KeyStore.getInstance(KeyStore.getDefaultType()) }
        })
    }

    fun startWebserver() {
        server.start(false)
    }

    fun stopWebserver() {
        server.stop(300, 500)
    }

}