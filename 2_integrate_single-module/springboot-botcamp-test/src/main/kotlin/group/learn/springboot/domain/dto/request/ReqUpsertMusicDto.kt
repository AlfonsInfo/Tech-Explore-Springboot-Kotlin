package group.learn.springboot.domain.dto.request

import group.learn.springboot.domain.entity.UserTypeEntity
import jakarta.persistence.Column
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.*

data class ReqUpsertMusicDto (

    var musicName : String ? = null,
    var musicLyric : String? = null,
    var type : String? = "T002",
    var idGenre : List<String> ? = null,

)