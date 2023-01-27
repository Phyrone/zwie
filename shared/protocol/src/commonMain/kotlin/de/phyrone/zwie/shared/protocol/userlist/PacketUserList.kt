package de.phyrone.zwie.shared.protocol.userlist

import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Serializable
data class PacketUserList(
    @ProtoNumber(0x01)
    val userAdd: UserAdd? = null,
    @ProtoNumber(0x02)
    val userRemove: UserRemove? = null,
    @ProtoNumber(0x11)
    val userUpdateStatus: UserUpdateStatus? = null,

) {

    @Serializable
    data class UserAdd(
        @ProtoNumber(1)
        val uid: String,
        @ProtoNumber(2)
        val displayname: String,
    )

    @Serializable
    data class UserRemove(
        @ProtoNumber(1)
        val uid: String,

        @ProtoNumber(2)
        val reason: String? = null
    )

    @Serializable
    data class UserUpdateStatus(
        @ProtoNumber(1)
        val uid: String,
        @ProtoNumber(2)
        val status: String //TODO Enum
    )
}