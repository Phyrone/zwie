package de.phyrone.zwie.server.main

import ch.qos.logback.classic.Level as LogbackLevel
import de.phyrone.musicnova.misc.MainThreadExecutor
import de.phyrone.zwie.server.utils.setLogLevel
import org.koin.mp.KoinPlatformTools
import org.slf4j.event.Level
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import java.io.File

@Command(
    name = "",
    mixinStandardHelpOptions = true,
)
class StartupArguments : Runnable {


    @Option(
        names = ["-c", "--config"],
        defaultValue = "defaults.conf",
    )
    lateinit var configFile: File


    @Option(
        names = ["-l", "--log-level"],
        defaultValue = "info"
    )
    lateinit var logLevel: Level
    override fun run() {
        setLogLevel(LogbackLevel.convertAnSLF4JLevel(logLevel))
        BootstrapTask(this).run()
        KoinPlatformTools.defaultContext().get()
            .get<MainThreadExecutor>().runLoop()
    }
}