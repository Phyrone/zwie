package de.phyrone.zwie.shared.protocol.rpc

import de.phyrone.zwie.shared.protocol.coder.PacketDecoder
import de.phyrone.zwie.shared.protocol.coder.PacketEncoder
import kotlinx.coroutines.flow.Flow
import kotlin.properties.ReadOnlyProperty

interface CallInterface : RemoteCallInterface, CallImplInterface