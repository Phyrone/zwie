package de.phyrone.zwie.server.scheduler

import de.phyrone.zwie.server.database.entity.ChannelChatMessageEntity
import de.phyrone.zwie.server.database.tables.ChannelChatMessagesTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNull
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.quartz.Job
import org.quartz.JobExecutionContext
import java.sql.Connection
import java.time.Duration as JavaDuration

import java.time.LocalDateTime
import kotlin.time.toJavaDuration
import kotlin.time.Duration as KotlinDuration

@SchedulerJob("cleanup-deleted-chat-messages", "core::channel::chat")
class CleanupDeletedChatMessagesJob : Job, KoinComponent {
    private val database by inject<Database>()
    override fun execute(context: JobExecutionContext) {

        val deadline = when (val deadlineAny = context.mergedJobDataMap["deadline"]) {
            null -> LocalDateTime.now()
            is JavaDuration -> LocalDateTime.now().minus(deadlineAny)
            is KotlinDuration -> LocalDateTime.now().minus(deadlineAny.toJavaDuration())
            is LocalDateTime -> deadlineAny
            else -> throw IllegalArgumentException(
                """
                deadline must be a one of the following types: java.time.LocalDateTime, kotlin.time.Duration, java.time.Duration or null
                """.trimIndent()
            )
        }
        val channelFilter = when (val channelFilter = context.mergedJobDataMap["channel"]) {
            null -> Op.TRUE //all tables
            is Long -> ChannelChatMessagesTable.channel eq channelFilter
            is Iterable<*> -> ChannelChatMessagesTable.channel inList channelFilter.filterIsInstance<Long>()
            is Array<*> -> ChannelChatMessagesTable.channel inList channelFilter.filterIsInstance<Long>()
            is String -> ChannelChatMessagesTable.channel inList channelFilter.trim().split(",", ";")
                .mapNotNull { it.trim().toLongOrNull() }

            else -> throw IllegalArgumentException(
                """
                channelFilter must be a one of the following types: Long, Long[], Iterable<Long>, String("1,2 etc.") or null
                """.trimIndent()
            )
        }

        Connection.TRANSACTION_READ_COMMITTED
        transaction(database) {
            ChannelChatMessageEntity.find {
                //find all messages that are deleted and older than 14 days
                ChannelChatMessagesTable.message.isNull() and channelFilter and
                        (ChannelChatMessagesTable.timeStamp lessEq deadline)
            }.forEach { it.deleteMessage() }

        }

    }
}