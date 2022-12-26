@file:JsExport
@file:Suppress("unused")

package de.phyrone.zwie.client.backend

import de.phyrone.zwie.shared.con.BuildConst
import kotlin.js.Promise

@Suppress("NON_EXPORTABLE_TYPE")
typealias EmptyPromise = Promise<Unit>

interface StatefulValue<T> {
    val value: T
    fun addListener(listener: (T) -> Unit): Disposable

    fun waitUntil(predicate: (T) -> Boolean): Promise<T>
}

interface MutableStatefulValue<T> : StatefulValue<T> {
    override var value: T
    fun update(update: (T) -> T)


}


object Zwie {

    @Suppress("NON_EXPORTABLE_TYPE")
    fun ready(): EmptyPromise = TODO()
    fun version(): String = BuildConst.VERSION
    fun kotlinVersion(): String = BuildConst.KOTLIN_VERSION

    fun getServer(id: String): ServerLogic? = TODO()

    fun getServers(): StatefulValue<Array<ServerLogic>> = TODO()

    fun getUser(userID: String): UserLogic? = TODO()
}


interface Disposable {
    fun dispose()
}

interface ServerLogic : Disposable {
    val id: String
    val name: StatefulValue<String>
    val nickname: MutableStatefulValue<String>

    fun channels(): StatefulValue<Array<ChannelLogic>>
    fun channel(id: String): ChannelLogic?
}

interface ChannelLogic : ChatLogic

interface ChatLogic {

    fun getMessages(): Array<ChatMessage>

    val latestMessage: StatefulValue<Int>

    @Suppress("NON_EXPORTABLE_TYPE")
    fun sendMessage(message: String): EmptyPromise
}

interface ChatMessage {
    val id: Int
    val author: String
    val message: String
    //val timestamp: String
}

interface UserLogic {
    val uid: String
    val name: StatefulValue<String>
    val nickname: MutableStatefulValue<String>

}

