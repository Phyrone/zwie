package de.phyrone.zwie.server.data.packets

import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.atteo.classindex.IndexSubclasses

@IndexSubclasses
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "id")
sealed interface Packet