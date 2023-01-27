package de.phyrone.zwie.shared.protocol.rpc

suspend inline operator fun <Res> RemoteCall<Unit, Res>.invoke(): Res = invoke(Unit)