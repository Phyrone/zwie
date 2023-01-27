package de.phyrone.zwie.server.misc

import de.phyrone.zwie.server.utils.lazyArg
import io.rsocket.kotlin.RSocketLoggingApi
import io.rsocket.kotlin.logging.Logger
import io.rsocket.kotlin.logging.LoggerFactory
import io.rsocket.kotlin.logging.LoggingLevel
import java.util.logging.Level

@OptIn(RSocketLoggingApi::class)
object RSocketFLoggerFactory : LoggerFactory {

    override fun logger(tag: String): Logger = LoggerImpl(tag)

    class LoggerImpl(
        override val tag: String,
    ) : Logger {
        private val logger = de.phyrone.zwie.server.utils.logger(tag)

        override fun isLoggable(level: LoggingLevel): Boolean = logger.at(level.toJavaLevel()).isEnabled

        override fun rawLog(level: LoggingLevel, throwable: Throwable?, message: Any?) {
            logger.at(level.toJavaLevel()).withCause(throwable).log("%s", lazyArg { message })
        }
    }

    private fun LoggingLevel.toJavaLevel() = when (this) {
        LoggingLevel.TRACE -> Level.FINER
        LoggingLevel.DEBUG -> Level.FINE
        LoggingLevel.INFO -> Level.INFO
        LoggingLevel.WARN -> Level.WARNING
        LoggingLevel.ERROR -> Level.SEVERE
    }

}