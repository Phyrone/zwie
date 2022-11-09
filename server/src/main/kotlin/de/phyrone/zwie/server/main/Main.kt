@file:JvmName("Main")

package de.phyrone.zwie.server.main

import de.phyrone.zwie.server.misc.InstanceLoader
import de.phyrone.zwie.server.misc.SlfKoinLogger
import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.server.utils.logger
import org.atteo.classindex.ClassIndex
import org.fusesource.jansi.AnsiConsole
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module
import picocli.CommandLine
import kotlin.system.exitProcess

private val logger = logger()
fun main(args: Array<String>) {
    if (System.getProperty("ansi.disable")?.toBooleanStrictOrNull() != true)
        AnsiConsole.systemInstall()

    startKoin {
        logger(SlfKoinLogger())
        modules(module(true) {
            single { this@startKoin }
            single { get<KoinApplication>().koin }
        })
    }
    val commandline = CommandLine(StartupArguments::class.java)
    commandline.isCaseInsensitiveEnumValuesAllowed = true
    try {
        exitProcess(commandline.execute(*args))
    } catch (e: Throwable) {
        logger.atSevere().withCause(e).log("Startup Failed!")
        exitProcess(1)
    }
}