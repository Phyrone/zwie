package de.phyrone.zwie.server.utils

import com.google.common.flogger.FluentLogger
import com.google.common.flogger.LazyArg
import com.google.common.flogger.backend.LoggerBackend
import com.google.common.flogger.backend.Platform
import com.mojang.brigadier.CommandDispatcher
import de.phyrone.zwie.server.command.CommandContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName

typealias ZwieCommandDispatcher = CommandDispatcher<CommandContext>

private val floggerConsturctor by lazy {
    FluentLogger::class.java.getDeclaredConstructor(LoggerBackend::class.java).also {
        it.isAccessible = true
    }
}

suspend inline fun <T : Any> ioTask(crossinline ioTask: () -> T): T = withContext(Dispatchers.IO) { ioTask() }

inline fun <T> lazyArg(crossinline lazy: () -> T?) = LazyArg<T> { lazy() }

@Suppress("NOTHING_TO_INLINE")
inline fun logger(): FluentLogger = FluentLogger.forEnclosingClass()
inline fun <reified T> loggerOf() = logger(T::class)
fun logger(clazz: KClass<*>) = floggerConsturctor.newInstance(Platform.getBackend(clazz.qualifiedName ?: clazz.jvmName))
fun logger(clazz: Class<*>) = floggerConsturctor.newInstance(Platform.getBackend(clazz.name))
fun logger(name: String) = floggerConsturctor.newInstance(Platform.getBackend(name))