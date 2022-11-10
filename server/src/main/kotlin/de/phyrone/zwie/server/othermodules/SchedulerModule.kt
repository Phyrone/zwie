package de.phyrone.zwie.server.othermodules

import com.coreoz.wisp.Scheduler
import com.coreoz.wisp.SchedulerConfig
import de.phyrone.zwie.server.module.CommonModule
import de.phyrone.zwie.server.module.Module
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinApplication
import org.koin.core.component.inject
import org.koin.dsl.module

@Module(
    name = "core::scheduler"
)
class SchedulerModule : CommonModule {


    private val koinApplication by inject<KoinApplication>()

    override suspend fun onEnable() {
        koinApplication.modules(koinModule)


    }

    override suspend fun onDisable() {
        withContext(Dispatchers.IO) {
            getKoin().getOrNull<Scheduler>()?.gracefullyShutdown()
            //getKoin().getOrNull<ScheduledExecutorService>()?.shutdown()
        }
        koinApplication.unloadModules(koinModule)
    }

    companion object {
        private val koinModule = module {
            single {
                SchedulerConfig.builder()
                    .minThreads(1)
                    .maxThreads(10)
                    .build()
            }
            //single { Executors.newScheduledThreadPool(2) }
            single { Scheduler(get<SchedulerConfig>()) }
        }
    }

}