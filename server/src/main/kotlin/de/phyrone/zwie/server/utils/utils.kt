@file:JvmMultifileClass

package de.phyrone.zwie.server.utils


import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.google.common.flogger.FluentLogger
import com.google.common.flogger.LazyArg
import com.google.common.flogger.backend.LoggerBackend
import com.google.common.flogger.backend.Platform
import com.mojang.brigadier.CommandDispatcher
import de.phyrone.zwie.server.command.CommandContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.atteo.classindex.ClassIndex
import ch.qos.logback.classic.Logger as LogbackLogger
import org.slf4j.Logger as Slf4jLogger
import ch.qos.logback.classic.Level as LogbackLevel
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName

typealias ZwieCommandDispatcher = CommandDispatcher<CommandContext>

private val floggerConsturctor by lazy {
    FluentLogger::class.java.getDeclaredConstructor(LoggerBackend::class.java).also {
        it.isAccessible = true
    }
}


fun <T : ObjectMapper> T.findAndRegisterSubtypesAndModules(): T {
    findAndRegisterSubtypes()
    findAndRegisterModules()
    registerKotlinModule()
    return this
}

@Suppress("SpreadOperator")
fun <T : ObjectMapper> T.findAndRegisterSubtypes(): T {
    registerSubtypes(*ClassIndex.getSubclasses(JsonComponent::class.java).toList().toTypedArray())
    return this
}

fun setLogLevel(level: LogbackLevel) {
    (LoggerFactory.getLogger(Slf4jLogger.ROOT_LOGGER_NAME) as? LogbackLogger)
        ?.level = level
}

suspend inline fun <T : Any> ioTask(crossinline ioTask: () -> T): T = withContext(Dispatchers.IO) { ioTask() }

inline fun <T> lazyArg(crossinline lazy: () -> T?) = LazyArg<T> { lazy() }

@Suppress("NOTHING_TO_INLINE")
inline fun logger(): FluentLogger = FluentLogger.forEnclosingClass()
inline fun <reified T> loggerOf() = logger(T::class)
fun logger(clazz: KClass<*>): FluentLogger =
    floggerConsturctor.newInstance(Platform.getBackend(clazz.qualifiedName ?: clazz.jvmName))

fun logger(clazz: Class<*>): FluentLogger = floggerConsturctor.newInstance(Platform.getBackend(clazz.name))
fun logger(name: String): FluentLogger = floggerConsturctor.newInstance(Platform.getBackend(name))