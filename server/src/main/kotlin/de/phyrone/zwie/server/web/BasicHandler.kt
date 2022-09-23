package de.phyrone.zwie.server.web

import io.vertx.ext.web.Router
import org.springframework.beans.factory.DisposableBean
import org.springframework.stereotype.Component

@Component
class BasicHandler(router: Router) : DisposableBean {
    val handler = router.get("/").handler { ctx ->
        ctx.response().statusCode = 403
        ctx.response().end("this is no webserver")
    }

    override fun destroy() {
        handler.disable()
    }
}