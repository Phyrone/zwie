package de.phyrone.zwie.server.web

import com.typesafe.config.Config
import com.typesafe.config.ConfigException
import de.phyrone.zwie.server.misc.RSocketFLoggerFactory
import de.phyrone.zwie.server.module.DependsOn
import de.phyrone.zwie.server.module.DisableTaskRunner
import de.phyrone.zwie.server.module.EnableTaskRunner
import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.server.utils.DEFAULT_PORT
import de.phyrone.zwie.server.utils.logger
import de.phyrone.zwie.server.web.WebModule.Companion.setupDefault
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import io.rsocket.kotlin.ktor.server.RSocketSupport
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.bind
import org.koin.dsl.module
import org.slf4j.event.*
import java.time.Duration
import java.util.zip.Deflater

@Module("core::web")
@DependsOn("core::config")
class WebModule : EnableTaskRunner, DisableTaskRunner, KoinComponent {

    private val koinApplication by inject<KoinApplication>()
    private val applicationEngine by inject<ApplicationEngine>()
    override suspend fun onEnable() {
        koinApplication.modules(koinModule)
        applicationEngine.start(false)
        if (logger.atInfo().isEnabled)
            CoroutineScope(Dispatchers.Main).launch {
                applicationEngine.resolvedConnectors().forEach { connector ->
                    logger.atInfo()
                        .log(
                            "listen on on %s://%s:%d/",
                            connector.type.name.lowercase(),
                            connector.host,
                            connector.port
                        )
                }
            }
    }

    override suspend fun onDisable() {
        applicationEngine.stop(200, 400)
        //koinApplication.unloadModules(koinModule)
    }

    companion object {
        private val logger = logger()

        private fun Application.setupDefault() {
            install(WebSockets) {
                pingPeriod = Duration.ofSeconds(15)
                timeout = Duration.ofSeconds(15)
                maxFrameSize = Long.MAX_VALUE
                masking = false
                extensions {
                    install(WebSocketDeflateExtension) {
                        compressionLevel = Deflater.BEST_COMPRESSION
                        compressIfBiggerThan(1024)
                    }
                }
            }
            install(RSocketSupport) {
                server {
                    this.loggerFactory = RSocketFLoggerFactory
                }
            }
            install(Authentication)

            install(CORS) {
                allowMethod(HttpMethod.Get)
                allowMethod(HttpMethod.Post)
                allowSameOrigin = true
                allowCredentials = true
                //allowMethod(HttpMethod.Options)
                //allowMethod(HttpMethod.Put)
                //allowMethod(HttpMethod.Delete)
                //allowMethod(HttpMethod.Patch)
                //allowHeader(HttpHeaders.Authorization)
                //allowHeader("MyCustomHeader")
                anyHost()
            }
        }

        private val koinModule = module {
            single {
                val config = get<Config>()
                applicationEngineEnvironment {
                    fromConfig(config)
                    module { setupDefault() }
                }
            } bind ApplicationEngineEnvironment::class
            single { embeddedServer(Netty, get()) } bind ApplicationEngine::class
            single { get<ApplicationEngine>().application }
        }

        private fun ApplicationEngineEnvironmentBuilder.fromConfig(config: Config) {

            try {
                config.getStringList("hosts").map { stringToHostPortPair(it) }
                    .distinct().forEach { (host, port) ->
                        connector {
                            this.host = host
                            this.port = port
                        }
                    }
            } catch (_: ConfigException) {
                try {
                    val (host, port) = stringToHostPortPair(config.getString("host"))
                    connector {
                        this.host = host
                        this.port = port
                    }
                } catch (_: ConfigException) {
                    connector {
                        port = DEFAULT_PORT
                    }
                }
            }
        }

        private fun stringToHostPortPair(hostPortString: String): Pair<String, Int> {
            val index = hostPortString.lastIndexOf(':')
            val port = (
                    if (index > -1) hostPortString.substring(index + 1).toIntOrNull()
                    else hostPortString.toIntOrNull()
                    )?.takeIf { it in UShort.MIN_VALUE.toInt()..UShort.MAX_VALUE.toInt() }

            val host = when {
                index > -1 -> hostPortString.substring(0, index)
                port == null -> hostPortString
                else -> "0.0.0.0"
            }
            return host to (port ?: DEFAULT_PORT)
        }
    }
}