package de.phyrone.zwie.shared.protocol.rpc

import kotlinx.coroutines.flow.Flow

interface RemoteStreamCall<Req, Res> : RemoteCall<Req, Flow<Res>>