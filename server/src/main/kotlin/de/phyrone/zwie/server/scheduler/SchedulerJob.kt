package de.phyrone.zwie.server.scheduler

import org.atteo.classindex.IndexAnnotated


@IndexAnnotated
annotation class SchedulerJob(
    val name: String,
    val group: String = "",
)
