@file:JvmName("Main")

package de.phyrone.zwie.server.main

import de.phyrone.zwie.server.misc.SlfKoinLogger
import org.fusesource.jansi.AnsiConsole
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module
import picocli.CommandLine
import kotlin.system.exitProcess

//private val logger = logger()
fun main(args: Array<String>) {
    if (System.getProperty("jansi.disable")?.toBooleanStrictOrNull() != true)
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
    commandline.colorScheme = CommandLine.Help.defaultColorScheme(CommandLine.Help.Ansi.ON)

    @Suppress("SpreadOperator") //unavoidable
    exitProcess(commandline.execute(*args))
}