package group.learn.springboot.domain.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "mst_profile")
data class ProfileEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id")
    var id : UUID? = null,


    @Column(name = "name")
    var name : String ? = null,

    @Column(name="username", unique = true)
    var username : String ? = null,

    @Column(name="email", unique = true)
    var email : String ? = null,

    @Column(name="password")
    var password : String ? = null,

    @Column(name="avatar", columnDefinition = "TEXT")
    var avatar : String ? = null


)
