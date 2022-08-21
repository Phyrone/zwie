package de.phyrone.zwie.server.web

import de.phyrone.zwie.server.utils.logger
import io.ktor.server.engine.*
import org.springframework.beans.factory.DisposableBean
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class WebRunner(
    val applicationEngine: ApplicationEngine,
) : DisposableBean, ApplicationRunner {

    companion object {
        val logger = logger()
    }

    override fun destroy() {
        applicationEngine.stop(300, 500)
    }

    override fun run(args: ApplicationArguments?) {
        applicationEngine.start(false)
    }

}