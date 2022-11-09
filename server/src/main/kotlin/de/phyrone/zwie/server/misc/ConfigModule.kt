package de.phyrone.zwie.server.misc

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import com.typesafe.config.ConfigRenderOptions
import de.phyrone.zwie.server.main.StartupArguments
import de.phyrone.zwie.server.module.DisableTaskRunner
import de.phyrone.zwie.server.module.EnableTaskRunner
import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.server.utils.logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.io.IOException
import org.koin.core.module.Module as KoinModule

@Module(
    name = "core::config"
)
class ConfigModule : EnableTaskRunner, DisableTaskRunner, KoinComponent {

    private val koinApplication by inject<KoinApplication>()


    private var koinModule: KoinModule? = null
    override suspend fun onEnable() {
        koinModule = module(true) {
            single(named("core::config::defaults")) { ConfigFactory.load("defaults") }
            single { loadConfig(get(), get(named("core::config::defaults"))) }
        }.also { koinApplication.modules(it) }
        val config = get<Config>()
        if (logger.atFine().isEnabled) {
            logger.atFine().log("Config: %s", config.root().render(ConfigRenderOptions.defaults()))
        }
    }

    override suspend fun onDisable() {

        koinModule?.let { koinApplication.unloadModules(it) }
    }

    private fun loadConfig(args: StartupArguments, defaults: Config): Config {
        val configFile = args.configFile
        var config: Config
        if (configFile.exists() && configFile.isFile) {
            logger.atInfo().log("Loading Config from %s...", configFile.absolutePath)
            try {
                config = ConfigFactory.parseFile(configFile)
            } catch (e: IOException) {
                config = ConfigFactory.empty()
                logger.atWarning().withCause(e)
                    .log("Failed to load Config from %s, using defaults", configFile.absolutePath)
            }
            logger.atInfo().log("Config loaded!")
        } else {
            logger.atInfo().log("Config file not found, using defaults")
            config = ConfigFactory.empty()
        }
        return config.withFallback(defaults)
    }

    companion object {
        private val logger = logger()
    }

}