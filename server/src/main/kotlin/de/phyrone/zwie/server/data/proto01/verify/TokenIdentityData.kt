package de.phyrone.zwie.server.data.proto01.verify

import com.fasterxml.jackson.annotation.JsonTypeName


//intended for bots which only need a serverside verification
@JsonTypeName("token")
data class TokenIdentityData(
    val token: String,
)
