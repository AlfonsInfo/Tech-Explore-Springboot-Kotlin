package group.learn.springboot.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "mst_favorite")
data class FavouriteEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id_favorite")
    var id : UUID? = null,


    @Column(name = "id_music")
    var idMusic : String ? = null,

    @Column(name = "user_added")
    var userAdded : String ? = null,


    @Column(name = "dt_added")
    var dtAdded : LocalDateTime ? = null,

    @Column(name = "dt_updated")
    var dtUpdated : LocalDateTime ? = null,

    )
