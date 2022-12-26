package de.phyrone.zwie.server.data.proto01.packets

import com.fasterxml.jackson.annotation.JsonTypeInfo
import de.phyrone.zwie.server.utils.JsonComponent

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_ARRAY)
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
interface Packet : JsonComponent