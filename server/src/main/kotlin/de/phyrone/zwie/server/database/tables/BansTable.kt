package de.phyrone.zwie.server.database.tables

import de.phyrone.zwie.server.database.IndexTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

@IndexTable
object BansTable : LongIdTable("ban") {

    val user = reference("user", UsersTable, ReferenceOption.RESTRICT, ReferenceOption.CASCADE).nullable().default(null)


    val ip4Address = binary("ip4_address", 4).nullable().default(null)
    val ip4Subnet = byte("ip4_subnet").nullable().default(null)
    val ip6Address = binary("ip6_address", 16).nullable().default(null)
    val up6Subnet = byte("ip6_subnet").nullable().default(null)

    val startDate = datetime("start_date").clientDefault { LocalDateTime.now() }
    val endDate = datetime("end_date").nullable().default(null)

    val reason = text("reason").default("")
}