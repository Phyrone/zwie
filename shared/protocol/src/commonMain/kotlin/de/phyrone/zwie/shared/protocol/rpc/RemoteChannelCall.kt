package de.phyrone.zwie.shared.protocol.rpc

import kotlinx.coroutines.flow.Flow

interface RemoteChannelCall<Req, ChannelIN, ChannelOUT> : RemoteCall<Req, ZChannel<ChannelIN, ChannelOUT>>