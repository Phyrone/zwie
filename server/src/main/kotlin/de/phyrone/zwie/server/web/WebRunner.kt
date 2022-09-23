package de.phyrone.zwie.server.web

import de.phyrone.zwie.server.utils.logger
import io.vertx.core.http.HttpServer
import io.vertx.kotlin.coroutines.await
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.DisposableBean
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class WebRunner(
    val server: HttpServer,
) : DisposableBean, ApplicationRunner {

    companion object {
        val logger = logger()
    }

    override fun destroy() {
        runBlocking { server.close().await() }
    }

    override fun run(args: ApplicationArguments?) {
        logger.atInfo().log("Starting WebServer...")
        server.listen(3344)
            .andThen { logger.atInfo().log("WebServer started!") }
    }

}