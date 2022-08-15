@file:JvmName("Main")

package de.phyrone.zwie.server

import com.google.common.flogger.LazyArg
import com.google.common.util.concurrent.ThreadFactoryBuilder
import de.phyrone.zwie.server.event.EventListener
import de.phyrone.zwie.server.event.KoinStartupDoneEvent
import de.phyrone.zwie.server.event.StartupDoneEvent
import de.phyrone.zwie.server.utils.EventBusLogger
import de.phyrone.zwie.server.utils.KoinLogger
import de.phyrone.zwie.server.utils.loadAnnotated
import de.phyrone.zwie.server.utils.logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import org.greenrobot.eventbus.EventBus
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext


val logger = logger()
fun main(args: Array<String>) {

    //there is no main thread
    Thread.currentThread().name = "Bootstrap"
    logger.atInfo().log("Starting Zwie Server...")
    val koinApplication = startKoin {
        logger(KoinLogger)
        modules(module {
            single(createdAtStart = true) { this@startKoin }
            single(createdAtStart = true) { get<KoinApplication>().koin }

            single(createdAtStart = true) { createWorkerPool() } bind ScheduledExecutorService::class bind ExecutorService::class bind Executor::class
            single(createdAtStart = true) { get<ExecutorService>().asCoroutineDispatcher() } bind CoroutineDispatcher::class bind CoroutineContext::class
            single(createdAtStart = true) { createEventbus(get()) }
        })
    }
    val eventbus = koinApplication.koin.get<EventBus>()
    eventbus.post(KoinStartupDoneEvent(koinApplication, koinApplication.koin))
    ShutdownHandler.add { WebServerHandler.stopWebserver() }
    WebServerHandler.startWebserver()
    eventbus.post(StartupDoneEvent())
    /*

    val r = PeerConnectionFactory().createPeerConnection(
        RTCConfiguration().also { rtcConfiguration ->
            rtcConfiguration.iceTransportPolicy = RTCIceTransportPolicy.RELAY
            rtcConfiguration.iceServers = listOf(
                RTCIceServer().also { }
            )
            rtcConfiguration;
        }, PeerConnectionObserver { candidate ->
            candidate;
        }
    )
    embeddedServer(Netty) {
        routing {
            webSocket {

               val frame =  incoming.receive()

                if(frame is Frame.Text){

                }
            }
        }
    }
     */
}

private fun createEventbus(asyncExecutor: ExecutorService): EventBus {

    val eventBus = EventBus.builder()
        .eventInheritance(true)
        .sendNoSubscriberEvent(true)
        .sendSubscriberExceptionEvent(true)

        .logSubscriberExceptions(true)
        .logSubscriberExceptions(true)

        .logger(EventBusLogger)
        .executorService(asyncExecutor)
        .installDefaultEventBus()

    loadAnnotated<EventListener, Any>().forEach { listener ->
        try {
            eventBus.register(listener)
        } catch (e: Exception) {
            logger.atWarning().log("Could not register listener %s", LazyArg { listener.javaClass.name })
        }
    }
    return eventBus
}

private fun createWorkerPool(
    size: Int = (Runtime.getRuntime().availableProcessors().coerceAtLeast(2) * 2),
): ScheduledThreadPoolExecutor {
    val factory = ThreadFactoryBuilder()
        .setDaemon(false)
        .setNameFormat("worker-%d")
        .setPriority(Thread.NORM_PRIORITY)
        .build()

    return ScheduledThreadPoolExecutor(size, factory).also { scheduledThreadPoolExecutor ->
        scheduledThreadPoolExecutor.maximumPoolSize = size
        scheduledThreadPoolExecutor.removeOnCancelPolicy = true
        scheduledThreadPoolExecutor.setKeepAliveTime(Long.MAX_VALUE, TimeUnit.MILLISECONDS)
        ShutdownHandler.add { scheduledThreadPoolExecutor.shutdown() }
    }
}