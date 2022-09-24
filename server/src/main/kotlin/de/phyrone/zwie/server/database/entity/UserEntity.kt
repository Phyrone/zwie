package de.phyrone.zwie.server.database.entity

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    lateinit var id: UUID

    lateinit var lastDisplayName: String

    lateinit var firstSeen: LocalDateTime

    lateinit var lastSeen: LocalDateTime

    @OneToOne(mappedBy = "user", optional = true)
    var tokenIdentity: UserIdentityToken? = null

}