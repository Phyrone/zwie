package de.phyrone.zwie.server.web

import io.vertx.core.Vertx
import io.vertx.core.http.HttpServerOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.sockjs.SockJSHandler
import io.vertx.kotlin.coroutines.await
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.security.KeyStore

@Component
class WebComponents {


    @Bean(destroyMethod = "close")
    fun vertx() = Vertx.vertx()


    @Bean
    fun httpServerOptions() = HttpServerOptions()

    @Bean
    fun httpServer(vertx: Vertx, router: Router, options: HttpServerOptions) = vertx.createHttpServer(options)
        .also { it.requestHandler(router) }

    @Bean
    fun sockJsHandler(vertx: Vertx) = SockJSHandler.create(vertx)

    @Bean
    fun router(vertx: Vertx) = Router.router(vertx)

    @Bean
    fun keystore(): KeyStore = KeyStore.getInstance(KeyStore.getDefaultType())

}