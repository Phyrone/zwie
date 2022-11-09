package de.phyrone.zwie.server.misc

import de.phyrone.zwie.server.utils.loggerOf
import org.koin.core.Koin
import org.koin.core.logger.Level
import org.koin.core.logger.MESSAGE
import java.lang.IllegalArgumentException
import org.koin.core.logger.Logger as KoinLogger


class SlfKoinLogger : KoinLogger(
    when {
        logger.atFiner().isEnabled -> Level.DEBUG
        logger.atFine().isEnabled -> Level.INFO
        logger.atSevere().isEnabled -> Level.ERROR
        else -> Level.NONE
    }
) {
    override fun log(level: Level, msg: MESSAGE) {
        when (level) {
            Level.DEBUG -> logger.atFiner()
            Level.INFO -> logger.atFine()
            Level.ERROR -> logger.atSevere()
            Level.NONE -> throw IllegalArgumentException("Level.NONE must not be used")
        }.log(msg)
    }

    companion object {
       private val logger = loggerOf<Koin>()
    }
}