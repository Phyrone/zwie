package de.phyrone.zwie.server.web.api1

import de.phyrone.zwie.server.event.WebSetupEvent
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class Api1Components {


    @EventListener(classes = [WebSetupEvent::class])
    fun WebSetupEvent.onWebSetupApi1() {
        with(builder) {
            module {
                routing {
                    get {
                        call.respondText("Hello World")
                    }
                    route("/api/v1/") {
                        get("/") {
                            call.respond("api v1 here")
                        }
                        webSocket("/socket") {

                        }
                    }
                }
            }
        }
    }


}