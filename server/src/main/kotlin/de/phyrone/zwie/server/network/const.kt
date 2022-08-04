package de.phyrone.zwie.server.network

object SocketCloseReason {
    private const val BASE: Short = 4000

    const val IDENTITY_INVALID: Short = (BASE + 21).toShort()
    const val IDENTITY_INSUFFICIENT_SECURITY_LEVEL: Short = (BASE + 22).toShort()

    const val CLIENT_KICKED: Short = (BASE + 401).toShort()
    const val CLIENT_BANNED: Short = (BASE + 402).toShort()

}