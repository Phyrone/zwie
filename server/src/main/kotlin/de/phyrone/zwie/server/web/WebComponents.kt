package de.phyrone.zwie.server.web

import de.phyrone.zwie.server.event.WebSetupEvent
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.websocket.*
import kotlinx.coroutines.CoroutineDispatcher
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.security.KeyStore

@Component
class WebComponents {

    @Bean
    @ConditionalOnMissingBean(ApplicationEngineEnvironment::class)
    fun webEnv(
        coroutineDispatcher: CoroutineDispatcher,
        publisher: ApplicationEventPublisher,
    ) = applicationEngineEnvironment {
        connector { port = 8080 }
        parentCoroutineContext = coroutineDispatcher

        module {
            install(WebSockets)

        }
        publisher.publishEvent(WebSetupEvent(this))
    }

    @Bean
    @ConditionalOnMissingBean(ApplicationEngine::class)
    fun server(
        applicationEngineEnvironment: ApplicationEngineEnvironment,
        publisher: ApplicationEventPublisher,
    ) = embeddedServer(Netty, applicationEngineEnvironment) {
        this.shareWorkGroup = true
        this.tcpKeepAlive = true
    }

    @Bean
    fun keystore(): KeyStore = KeyStore.getInstance(KeyStore.getDefaultType())

}