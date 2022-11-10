package de.phyrone.zwie.server.main

import de.phyrone.zwie.server.misc.MainThreadExecutor
import de.phyrone.zwie.server.misc.InstanceLoader
import de.phyrone.zwie.server.misc.ShutdownManager
import de.phyrone.zwie.server.module.EnableTaskRunner
import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.server.module.ModuleLoader
import de.phyrone.zwie.server.utils.lazyArg
import de.phyrone.zwie.server.utils.logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.atteo.classindex.ClassIndex
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.system.exitProcess
import kotlin.system.measureNanoTime
import kotlin.time.Duration.Companion.nanoseconds

class BootstrapTask(private val arguments: StartupArguments) : KoinComponent, Runnable {

    private val koinApplication by inject<KoinApplication>()
    private val moduleLoader by inject<ModuleLoader>()
    private val shutdownManager by inject<ShutdownManager>()

    override fun run(): Unit = runBlocking {
        suspend fun start() {
            koinApplication.modules(module {
                single { arguments }
                single(named("core::boot::modules")) {
                    InstanceLoader.getAll(ClassIndex.getAnnotated(Module::class.java)).toList()
                }
                single { ModuleLoader(get(named("core::boot::modules"))) }
                single { ShutdownManager(get()) }
                single { MainThreadExecutor() } bind MainCoroutineDispatcher::class
            })
            shutdownManager.init()
            withContext(Dispatchers.Default) {
                val errors = moduleLoader.handleOrdered(EnableTaskRunner)
                if (errors.isNotEmpty()) {
                    logger.atSevere().log("%d modules failed to enable! %s", errors.size, lazyArg {
                        errors.joinToString(
                            separator = ",",
                            prefix = "[",
                            postfix = "]",
                            limit = 4,
                            truncated = "..."
                        ) { it.first }
                    })
                    errors.forEach { (module, error) ->
                        logger.atSevere().withCause(error).log("Module %s failed to enable!", module)
                    }
                    exitProcess(1)
                }
            }
        }
        if (logger.atInfo().isEnabled) {
            val startupTime = measureNanoTime {
                start()
            }.nanoseconds
            logger.atInfo().log("Startup Done! (%s)", startupTime)
        } else {
            start()
        }


    }

    companion object {
        private val logger = logger()
    }


}