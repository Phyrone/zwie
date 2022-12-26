package de.phyrone.zwie.server.command

import com.mojang.brigadier.arguments.LongArgumentType
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.zaxxer.hikari.HikariDataSource
import de.phyrone.brig.wrapper.executes
import de.phyrone.brig.wrapper.literal
import de.phyrone.brig.wrapper.runs
import de.phyrone.zwie.server.database.tables.UsersTable
import de.phyrone.zwie.server.module.CommonModule
import de.phyrone.zwie.server.module.DependsOn
import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.server.utils.ZwieCommandDispatcher
import de.vandermeer.asciitable.AT_Context
import de.vandermeer.asciitable.AsciiTable
import de.vandermeer.asciithemes.TA_GridThemes
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SqlExpressionBuilder.lessEq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import org.jline.reader.LineReader
import org.jline.terminal.Terminal
import org.koin.core.component.inject
import java.sql.ResultSet
import java.sql.SQLSyntaxErrorException
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Module("core::commands::database")
@DependsOn("core::commands")
@DependsOn("core::database")
class DatabaseCommandsModule : CommonModule {

    private val database by inject<Database>()
    private val dataSource by inject<HikariDataSource>()
    private val commandDispatcher by inject<ZwieCommandDispatcher>()

    override suspend fun onEnable() {
        commandDispatcher.literal("database") {
            literal("users") {
                literal("cleanup") {
                    runs {
                        val deadLine = LocalDateTime.now().minusDays(90)
                        val removed = transaction(database) { UsersTable.deleteWhere { lastSeen lessEq deadLine } }
                        println("Removed $removed Users")//TODO replace with sender message
                    }

                    literal("older") {
                        literal("than") {
                            argument("amount", LongArgumentType.longArg(1)) {
                                suggest {
                                    suggest(1)
                                    repeat(3) { suggest((it + 1) * 5) }
                                    suggest(30)
                                    suggest(90)
                                    suggest(180)
                                    suggest(365)
                                }
                                argument("unit", StringArgumentType.word()) {
                                    suggest {
                                        ChronoUnit.values().map { it.name }.forEach { suggest(it) };
                                    }
                                    executes { context ->
                                        val unitString = StringArgumentType.getString(context, "unit")
                                        val amount = LongArgumentType.getLong(context, "amount")
                                        val unit = runCatching {
                                            ChronoUnit.valueOf(unitString.uppercase())
                                        }.getOrNull() ?: run {
                                            println(
                                                "Invalid Unit: ${
                                                    ChronoUnit.values().joinToString(",", "[", "]") { it.name }
                                                }"
                                            )//TODO replace with sender message
                                            return@executes 1
                                        }

                                        val deadLine = LocalDateTime.now().minus(
                                            amount, unit
                                        )
                                        require(
                                            deadLine.isBefore(LocalDateTime.now().minusMinutes(1))
                                        ) {
                                            "must be older than 1 minute"//TODO replace with sender message
                                        }

                                        val removed = transaction(
                                            database
                                        ) { UsersTable.deleteWhere { lastSeen lessEq deadLine } }
                                        println("Removed $removed Users")//TODO replace with sender message
                                        return@executes 0
                                    }
                                }
                            }
                        }
                    }
                }

            }
            literal("execute") {
                argument("query", StringArgumentType.greedyString()) {
                    runs { context ->
                        val query = StringArgumentType.getString(context, "query")
                        try {
                            dataSource.connection.use { connection ->
                                connection.createStatement().use { statement ->
                                    statement.execute(query)
                                    statement.resultSet
                                        ?.let {
                                            println(
                                                it.toTable().renderAdaptive()
                                            )
                                        }//TODO replace with sender message
                                }
                            }
                        } catch (e: SQLSyntaxErrorException) {
                            println(e.localizedMessage)//TODO replace with sender message
                        }
                    }
                }

            }
        }
    }

    private val asciiTableCtxField = AsciiTable::class.java.getDeclaredField("ctx").also { it.isAccessible = true }
    private fun AsciiTable.renderAdaptive() =
        render(getKoin().getOrNull<Terminal>()?.width?.takeIf { it > 20 }
                   ?: (asciiTableCtxField.get(this) as AT_Context).width)


    private fun ResultSet.toTable(): AsciiTable {

        val table = AsciiTable(AT_Context().setGridTheme(TA_GridThemes.FULL))
        table.addRule()
        val colCount = metaData.columnCount
        val cols = (1..colCount).map { metaData.getColumnName(it) }
        table.addRow("NR.", *cols.toTypedArray())
        table.addRule()
        while (next()) {
            table.addRow(row, *(1..colCount).map { getString(it) }.toTypedArray())
            table.addRule()
        }

        return table
    }
}