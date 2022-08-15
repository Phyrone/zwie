package de.phyrone.zwie.server.utils

import org.koin.core.logger.Level
import org.koin.core.logger.Level as KoinLevel
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE


private val logger = logger("Koin")

object KoinLogger : Logger(getKoinLoggerLevel()) {
    override fun log(level: KoinLevel, msg: MESSAGE) {
        when (level) {
            Level.ERROR -> logger.atSevere()
            Level.INFO -> logger.atInfo()
            Level.DEBUG -> logger.atFine()
            else -> return
        }.log(msg)
    }
}

private fun getKoinLoggerLevel(): KoinLevel = when {
    logger.atFine().isEnabled -> KoinLevel.DEBUG
    logger.atInfo().isEnabled -> KoinLevel.INFO
    logger.atSevere().isEnabled -> KoinLevel.ERROR
    else -> KoinLevel.NONE
}