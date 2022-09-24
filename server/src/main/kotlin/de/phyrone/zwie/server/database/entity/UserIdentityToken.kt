package de.phyrone.zwie.server.database.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class UserIdentityToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    //a sha512 hash of the token
    @Column(name = "token", nullable = false, length = 64)
    var token: String = ""

    @OneToOne
    lateinit var user: UserEntity

}