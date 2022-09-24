package de.phyrone.zwie.server.data.verify

import com.fasterxml.jackson.annotation.JsonTypeName


//important do not reuse the secret on another server
//its considered as a temporary verification until the cryptographic stuff is working
@JsonTypeName("shared_secret")
data class TokenIdentity(
    val secret: String,
)
