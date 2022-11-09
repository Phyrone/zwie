package de.phyrone.zwie.server.web

import com.typesafe.config.Config
import com.typesafe.config.ConfigException
import com.typesafe.config.ConfigFactory
import de.phyrone.zwie.server.module.DisableTaskRunner
import de.phyrone.zwie.server.module.EnableTaskRunner
import de.phyrone.zwie.server.module.Module
import io.ktor.http.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.bind
import org.koin.dsl.module

@Module(
    name = "core::web",
    dependencies = [
        Module.Dependency(
            name = "core::config",
        )
    ]

)
class WebModule : EnableTaskRunner, DisableTaskRunner, KoinComponent {

    private val koinApplication by inject<KoinApplication>()
    private val applicationEngine by inject<ApplicationEngine>()
    override suspend fun onEnable() {
        koinApplication.modules(koinModule)
        applicationEngine.start(false)
        applicationEngine.resolvedConnectors()
    }

    override suspend fun onDisable() {
        applicationEngine.stop(200, 400)
        koinApplication.unloadModules(koinModule)
    }

    companion object {
        private val koinModule = module {
            single {
                val config = get<Config>()
                applicationEngineEnvironment {
                    fromConfig(config)
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
            } catch (e: ConfigException) {
                try {
                    val (host, port) = stringToHostPortPair(config.getString("host"))
                    connector {
                        this.host = host
                        this.port = port
                    }
                } catch (e: ConfigException) {
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