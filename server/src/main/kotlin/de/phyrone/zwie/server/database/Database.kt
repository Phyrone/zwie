package de.phyrone.zwie.server.database

import de.phyrone.zwie.server.database.tables.IndexTable
import de.phyrone.zwie.server.utils.loadAnnotated
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table

internal fun initTables() {
    SchemaUtils.createMissingTablesAndColumns(*loadAnnotated<IndexTable, Table>().toTypedArray())
}