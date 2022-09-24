package de.phyrone.zwie.server.data.verify

import com.fasterxml.jackson.annotation.JsonTypeInfo
import de.phyrone.zwie.server.utils.JsonComponent

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
interface IdentityVerification : JsonComponent