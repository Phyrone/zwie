package de.phyrone.zwie.shared.protocol.intl

import io.rsocket.kotlin.ConnectionAcceptor
import io.rsocket.kotlin.ConnectionAcceptorContext
import io.rsocket.kotlin.RSocket

class AcceptorWrapper(

) : ConnectionAcceptor {
    override suspend fun ConnectionAcceptorContext.accept(): RSocket {
        TODO()
    }

}