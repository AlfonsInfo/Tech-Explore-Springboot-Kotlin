package group.learn.springboot.domain.dto.request

import jakarta.persistence.Column

data class ReqUpsertGenreDto (
    var name : String ? = null,
    var desc : String ? = null
)