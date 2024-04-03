package group.learn.springboot.domain.entity

import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "mst_user_type")
data class UserTypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id_type")
    var id : UUID? = null,


    @Column(name = "type_name", unique = true)
    var name : String ? = null,

    @Column(name = "type_desc")
    var desc : String ? = null,


    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    var users: List<UserEntity>? = null,

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    var music: List<MusicEntity>? = null


)
