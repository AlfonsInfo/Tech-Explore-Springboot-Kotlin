package group.learn.springboot.domain.entity

import jakarta.persistence.*
import java.util.UUID


@Entity
@Table(name = "mst_motor")
data class MotorEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id")
    var id : UUID? = null,
    @Column(name = "name")
    var name : String ? = null,
    @Column(name="merk")
    var merk : String ? = null
    )