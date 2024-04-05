package group.learn.springboot.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*


@Entity
@Table(name = "mst_music")
data class MusicEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id_music")
    var id : UUID? = null,

    @Column(name = "name_music")
    var musicName : String ? = null,

    @Column(name = "lyric")
    var lyric : String ? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type")
    var type : UserTypeEntity ? = null,

    @Column(name = "id_genre")
    var idGenre : String ? = null,

    @Column(name = "dt_added")
    var dtAdded : LocalDateTime ? = null,

    @Column(name = "dt_updated")
    var dtUpdated : LocalDateTime ? = null,


    )
