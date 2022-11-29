package de.phyrone.zwie.server.main

import com.google.common.util.concurrent.ThreadFactoryBuilder
import de.phyrone.zwie.server.gen.EventBusIndex
import de.phyrone.zwie.server.misc.EventBusLogger
import de.phyrone.zwie.server.misc.EventLogger
import de.phyrone.zwie.server.misc.InstanceLoader
import de.phyrone.zwie.server.misc.MainThreadExecutor
import de.phyrone.zwie.server.misc.ShutdownManager
import de.phyrone.zwie.server.module.EnableTaskRunner
import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.server.module.ModuleLoader
import de.phyrone.zwie.server.utils.lazyArg
import de.phyrone.zwie.server.utils.logger
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.atteo.classindex.ClassIndex
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.setMainThreadExecutor
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.binds
import org.koin.dsl.module
import oshi.SystemInfo
import oshi.hardware.CentralProcessor
import oshi.hardware.ComputerSystem
import oshi.hardware.GlobalMemory
import oshi.hardware.HardwareAbstractionLayer
import oshi.software.os.OSProcess
import oshi.software.os.OperatingSystem
import java.security.SecureRandom
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.ThreadFactory
import kotlin.coroutines.CoroutineContext
import kotlin.system.exitProcess
import kotlin.system.measureNanoTime
import kotlin.time.Duration.Companion.nanoseconds
import org.greenrobot.eventbus.MainThreadExecutor as EventbusMainThreadExecutor

class BootstrapTask(private val arguments: StartupArguments) : KoinComponent, Runnable {

    private val koinApplication by inject<KoinApplication>()
    private val moduleLoader by inject<ModuleLoader>()
    private val shutdownManager by inject<ShutdownManager>()
    private val operatingSystem by inject<OperatingSystem>()
    private val eventBus by inject<EventBus>()
    private val currentProcess by inject<OSProcess>()
    private val cpuInfo by inject<CentralProcessor>()
    private val memInfo by inject<GlobalMemory>()
    private val machineInfo by inject<ComputerSystem>()

    override fun run(): Unit = runBlocking {
        suspend fun start() {
            koinApplication.modules(module {
                single { arguments }
                single(named("core::boot::modules")) {
                    InstanceLoader.getAll(ClassIndex.getAnnotated(Module::class.java)).toList()
                }
                single { ModuleLoader(get(named("core::boot::modules"))) }
                single { ShutdownManager(get()) }
                single { MainThreadExecutor() } binds arrayOf(
                    MainCoroutineDispatcher::class,
                    Executor::class,
                    EventbusMainThreadExecutor::class
                )
                single {
                    EventBus.builder()
                        .eventInheritance(true)
                        .setMainThreadExecutor(get())
                        .addIndex(EventBusIndex())
                        .logger(EventBusLogger)
                        .executorService(get(named("core::threadpool::async")))
                        .logNoSubscriberMessages(true)
                        .logSubscriberExceptions(true)
                        .sendNoSubscriberEvent(true)
                        .sendSubscriberExceptionEvent(true)
                        .executorService(get(named("core::threadpool::async")))
                        .build()
                }
                single(named("core::threadpool::async")) {
                    ThreadFactoryBuilder()
                        .setDaemon(true)
                        .setNameFormat("Async-Worker-%d")
                        .build()
                }
                single(named("core::threadpool::async")) {
                    ScheduledThreadPoolExecutor(
                        Runtime.getRuntime().availableProcessors().coerceAtLeast(2),
                        get<ThreadFactory>(named("core::threadpool::async"))
                    )
                } binds arrayOf(ScheduledExecutorService::class, ExecutorService::class, Executor::class)

                single(named("core::threadpool::async")) {
                    get<ScheduledExecutorService>(named("core::threadpool::async")).asCoroutineDispatcher()
                } bind CoroutineContext::class
                single { SystemInfo() }
                single { get<SystemInfo>().operatingSystem }
                single { get<SystemInfo>().hardware }
                single { get<OperatingSystem>().let { os -> os.getProcess(os.processId) } }
                single { get<HardwareAbstractionLayer>().processor }
                single { get<HardwareAbstractionLayer>().memory }
                single { get<HardwareAbstractionLayer>().computerSystem }
                single { SecureRandom.getInstanceStrong() } bind SecureRandom::class

            })
            eventBus.register(EventLogger())
            if (logger.atFine().isEnabled) {
                logger.atFine().log("SystemInfo:")
                logger.atFine().log("  OS: %s", operatingSystem)
                logger.atFine().log(
                    "  Process: PID=%s UID=%s GID=%s",
                    currentProcess.processID,
                    currentProcess.userID,
                    currentProcess.groupID,
                )
                logger.atFine().log("  Hardware:")
                kotlin.run {
                    val identifier = cpuInfo.processorIdentifier

                    logger.atFine().log(
                        "    CPU: vendor=%s model=%s arch=%s 64bit=%b",
                        identifier.vendor,
                        identifier.model,
                        identifier.microarchitecture,
                        identifier.isCpu64bit,
                    )
                }
                logger.atFine().log("    Memory: %s", memInfo)

            }

            val uid = currentProcess.userID.toIntOrNull()
            when {
                uid == 0 && !arguments.allowRoot -> {
                    logger.atSevere().log("UID = 0 -> Running as root is not allowed! -> Exit")
                    logger.atFine().log("""Use "${StartupArguments.ALLOW_ROOT_OPTION}" to bypass this check""")
                    exitProcess(1)
                }

                uid == 0 -> logger.atWarning().log("UID = 0 -> Running as root is not recommended!")
                else -> {}
            }
            shutdownManager.init()

            withContext(get(named("core::threadpool::async"))) {
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