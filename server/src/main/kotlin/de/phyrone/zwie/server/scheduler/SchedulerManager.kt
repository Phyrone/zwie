package de.phyrone.zwie.server.scheduler

import de.phyrone.zwie.server.module.CommonModule
import de.phyrone.zwie.server.module.DependsOn
import de.phyrone.zwie.server.module.Module
import org.atteo.classindex.ClassIndex
import org.koin.core.KoinApplication
import org.koin.core.component.inject
import org.koin.dsl.bind
import org.koin.dsl.module
import org.quartz.*
import org.quartz.impl.DirectSchedulerFactory
import org.quartz.impl.jdbcjobstore.JobStoreTX
import org.quartz.spi.JobStore
import org.quartz.utils.ConnectionProvider
import org.quartz.utils.DBConnectionManager
import java.sql.Connection
import javax.sql.DataSource
import kotlin.reflect.full.findAnnotation


@Module("core::scheduler")
@DependsOn("core::database")
class SchedulerManager : CommonModule {

    private val koinApplication by inject<KoinApplication>()
    private val scheduler by inject<Scheduler>()
    override suspend fun onEnable() {
        koinApplication.modules(MODULE)
        ClassIndex.getAnnotated(SchedulerJob::class.java)
            .asSequence()
            .filter { Job::class.java.isAssignableFrom(it) }
            .map {
                @Suppress("UNCHECKED_CAST")
                it as Class<out Job>
            }.mapNotNull { clazz ->
                clazz.kotlin.findAnnotation<SchedulerJob>()?.let { annotation -> clazz to annotation }
            }.map { (clazz, annotation) ->
                JobBuilder.newJob(clazz)
                    .withIdentity(annotation.name, annotation.group.takeUnless { it.isBlank() })
                    .build()
            }.forEach { job -> scheduler.addJob(job, true) }
        scheduler.start()
    }

    override suspend fun onDisable() {
        scheduler.shutdown(true)
    }

    companion object {
        private const val DATASOURCE_NAME = "core"
        private val MODULE = module {
            single { DirectSchedulerFactory.getInstance().createScheduler(get(), get()) }
            single {
                binDataSource(get())
                JobStoreTX().also { jobStoreTX ->

                    jobStoreTX.dataSource = DATASOURCE_NAME
                }
            } bind JobStore::class

        }

        private fun binDataSource(dataSource: DataSource) {
            DBConnectionManager.getInstance().addConnectionProvider(DATASOURCE_NAME, object : ConnectionProvider {
                override fun getConnection(): Connection = dataSource.connection
                override fun shutdown() {}
                override fun initialize() {}
            })
        }
    }
}