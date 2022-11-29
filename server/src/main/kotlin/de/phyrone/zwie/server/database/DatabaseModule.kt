package de.phyrone.zwie.server.database

import com.typesafe.config.Config
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import de.phyrone.zwie.server.misc.InstanceLoader
import de.phyrone.zwie.server.module.DependsOn
import de.phyrone.zwie.server.module.DisableTaskRunner
import de.phyrone.zwie.server.module.EnableTaskRunner
import de.phyrone.zwie.server.module.Module
import de.phyrone.zwie.server.utils.logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.atteo.classindex.ClassIndex
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import java.io.Closeable
import java.util.concurrent.ScheduledExecutorService
import javax.sql.DataSource

@Module(
    name = "core::database",
)
@DependsOn("core::config")
class DatabaseModule : EnableTaskRunner, DisableTaskRunner, KoinComponent {

    private val koinApplication by inject<KoinApplication>()
    private val database by inject<Database>()
    private val dataSource by inject<DataSource>()


    override suspend fun onEnable() {
        koinApplication.modules(koinModule)
        val tables = InstanceLoader.getAll(ClassIndex.getAnnotated(IndexTable::class.java))
            .mapNotNull { it as? Table }.toList().toTypedArray()

        logger.atFine().log("SQL Dialect: %s", database.dialect.name)
        newSuspendedTransaction(Dispatchers.IO, database) {
            SchemaUtils.createMissingTablesAndColumns(
                tables = tables,
                withLogs = true,
                inBatch = true
            )
        }
    }

    override suspend fun onDisable() {
        withContext(Dispatchers.IO) {
            (dataSource as? Closeable)?.close()
        }
        koinApplication.unloadModules(koinModule)
    }

    companion object {
        private fun createHikariConfig(executor: ScheduledExecutorService, config: Config): HikariConfig {
            val hikariConfig = HikariConfig()
            //TODO configurable
            hikariConfig.jdbcUrl = "jdbc:h2:./zwie;AUTO_SERVER=TRUE"
            hikariConfig.driverClassName = "org.h2.Driver"
            hikariConfig.maximumPoolSize = 15
            hikariConfig.minimumIdle = 1
            hikariConfig.scheduledExecutor = executor

            hikariConfig.validate()
            return hikariConfig
        }

        private val koinModule = module {
            single { createHikariConfig(get(named("core::threadpool::async")), get()) }
            single { HikariDataSource(get()) } bind DataSource::class
            single { Database.connect(get<DataSource>()) }
        }
        private val logger = logger()
    }


}