package de.phyrone.zwie.server.misc

import de.phyrone.zwie.server.utils.loggerOf
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Logger
import java.util.logging.Level

object EventBusLogger : Logger {
    override fun log(level: Level, msg: String) {
        logger.at(level).log(msg)
    }

    override fun log(level: Level, msg: String, th: Throwable) {
        logger.at(level).withCause(th).log(msg)
    }

    private val logger = loggerOf<EventBus>()

}