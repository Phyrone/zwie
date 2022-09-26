package de.phyrone.zwie.server.data.packets

import com.fasterxml.jackson.annotation.JsonTypeInfo
import de.phyrone.zwie.server.utils.JsonComponent
import org.atteo.classindex.IndexSubclasses

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_ARRAY)
interface Packet : JsonComponent