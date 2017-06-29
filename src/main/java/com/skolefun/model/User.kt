package com.skolefun.model

import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(
        @Column(nullable = false) var username: String,
        @Column(nullable = false) var password: String,
        @ElementCollection(fetch = FetchType.EAGER) @Enumerated(EnumType.STRING) @Column(nullable = false, name = "role") var roles: MutableSet<Role> = hashSetOf(),
        @Column(nullable = false) var grade: Int,
        @Column(nullable = false) var classLetter: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}
