package de.phyrone.zwie.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.util.concurrent.ThreadFactoryBuilder
import de.phyrone.zwie.server.data.packets.Packet
import de.phyrone.zwie.server.event.StartupDoneEvent
import de.phyrone.zwie.server.utils.JsonIndex
import de.phyrone.zwie.server.utils.lazyArg
import de.phyrone.zwie.server.utils.logger
import kotlinx.coroutines.asCoroutineDispatcher
import org.atteo.classindex.ClassIndex
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import java.util.concurrent.Executor
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.ThreadFactory
import java.util.concurrent.TimeUnit
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@EnableAsync
@EnableScheduling
@SpringBootApplication
class ZwieServerApplication {

    companion object Main {
        private val logger = logger()


        @JvmStatic
        @OptIn(ExperimentalTime::class)
        fun main(args: Array<String>) {
            val application = SpringApplication(ZwieServerApplication::class.java)
            application.setHeadless(true)
            application.setAddCommandLineProperties(true)
            application.setLazyInitialization(false)
            val (applicationContext, bootstrap_time) = measureTimedValue { application.run(*args) }
            //logger.atInfo().log("Bootstrap: Done(%s)", lazyArg { bootstrap_time })
            applicationContext.publishEvent(StartupDoneEvent())
        }
    }


    @Bean
    fun workerPoolFactory(): ThreadFactory {
        return ThreadFactoryBuilder()
            .setDaemon(false)
            .setNameFormat("worker-%d")
            .setPriority(Thread.NORM_PRIORITY)
            .build()
    }

    @Bean(destroyMethod = "shutdown")
    fun workerPool(
        @Qualifier("workerPoolFactory") factory: ThreadFactory,
    ): ScheduledThreadPoolExecutor {
        //TODO configurable
        val size = (Runtime.getRuntime().availableProcessors().coerceAtLeast(2) * 2)

        return ScheduledThreadPoolExecutor(size, factory).also { scheduledThreadPoolExecutor ->
            scheduledThreadPoolExecutor.maximumPoolSize = size
            scheduledThreadPoolExecutor.removeOnCancelPolicy = true
            scheduledThreadPoolExecutor.setKeepAliveTime(Long.MAX_VALUE, TimeUnit.MILLISECONDS)
        }
    }

    @Bean
    fun coroutineContext(@Qualifier("workerPool") executor: Executor) = executor.asCoroutineDispatcher()

    @Bean
    fun objectMapper() = ObjectMapper().findAndRegisterModules().also { mapper ->
        mapper.registerSubtypes(ClassIndex.getSubclasses(Packet::class.java).toSet())
        mapper.registerSubtypes(ClassIndex.getAnnotated(JsonIndex::class.java).toSet())
        logger.atFine().log(
            "Registered %s json modules [%s]",
            lazyArg { mapper.registeredModuleIds.size },
            lazyArg { mapper.registeredModuleIds.joinToString(separator = ",") }
        )
    }
}