package de.phyrone.zwie.server.utils

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Logger
import java.util.logging.Level

object EventBusLogger : Logger {
    private val logger = logger(EventBus::class)
    override fun log(level: Level, msg: String) {
        logger.at(level).log(msg)
    }

    override fun log(level: Level, msg: String, th: Throwable) {
        logger.at(level).withCause(th).log(msg)
    }
}