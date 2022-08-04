package de.phyrone.zwie.server.instance

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import org.jetbrains.exposed.sql.Database
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject


class ServerInstance(val dispatcher: CoroutineDispatcher = Dispatchers.Default) : KoinComponent {
    private val serverCoroutineScope = CoroutineScope(dispatcher)
    private val database by inject<Database>()

    private val clientsLock = Mutex()

    fun start(){
        embeddedServer(Netty,port = 4433){
            routing{
                get("/"){
                    call.respondText("Hello World")
                }
            }
        }.start(true)
    }


}