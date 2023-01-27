package de.phyrone.zwie.shared.crypt.gpg.exception

open class GPGVerficationException : Exception {
    constructor() : super()

    constructor(message: String?) : super(message)

    constructor(message: String?, cause: Throwable?) : super(message, cause)

    constructor(cause: Throwable?) : super(cause)

}