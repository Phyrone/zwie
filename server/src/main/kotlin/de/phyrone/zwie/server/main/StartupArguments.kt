package de.phyrone.zwie.server.main

import ch.qos.logback.classic.Level as LogbackLevel
import de.phyrone.zwie.server.misc.MainThreadExecutor
import de.phyrone.zwie.server.utils.lazyArg
import de.phyrone.zwie.server.utils.logger
import de.phyrone.zwie.server.utils.setLogLevel
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
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

    @Option(
        names = ["-f", "--features"],


        )
    var features: Array<ServerFeature> = ServerFeature.values()


    //for debug purposes
    @Option(
        names = ["--no-boot"],
        hidden = true,
        echo = true
    )
    var noBoot = false


    @Option(
        names = [ALLOW_ROOT_OPTION],
        hidden = true,
        echo = true
    )
    var allowRoot = false
    override fun run() {
        setLogLevel(LogbackLevel.convertAnSLF4JLevel(logLevel))
        logger.atFine().log("StartArguments: %s", lazyArg {
            ToStringBuilder.reflectionToString(
                this,
                ToStringStyle.JSON_STYLE
            )
        })
        if (noBoot) return
        BootstrapTask(this).run()
        KoinPlatformTools.defaultContext().get()
            .get<MainThreadExecutor>().runLoop()
    }

    companion object {
        private val logger = logger()
        const val ALLOW_ROOT_OPTION = "--imRunningAsRootItIsEvilAndIKnowWhatImDoing"
    }
}