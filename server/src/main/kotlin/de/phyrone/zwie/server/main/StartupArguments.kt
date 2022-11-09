package de.phyrone.zwie.server.main

import de.phyrone.musicnova.misc.MainThreadExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import picocli.CommandLine.Command

@Command(
    name = ""
)
class StartupArguments : Runnable, KoinComponent {


    override fun run() {
        BootstrapTask(this).run()
        get<MainThreadExecutor>().runLoop()
    }
}