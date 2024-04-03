package group.learn.springboot.domain.entity

import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "mst_genre")
data class GenreEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_genre", )
    var id : Int? = null,


    @Column(name = "genre_name", unique = true)
    var name : String ? = null,

    @Column(name = "genre_desc")
    var desc : String ? = null
)
