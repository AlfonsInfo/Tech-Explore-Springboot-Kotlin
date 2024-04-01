package group.learn.springsecurity.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="customerId")
    var id : UUID? = null,

    @Column(name = "name")
    var name : String ? = null,

    @Column(name="username", unique = true)
    var username : String ? = null,

    @Column(name="email", unique = true)
    var email : String ? = null,

    @Column(name="password")
    var password : String ? = null,
)