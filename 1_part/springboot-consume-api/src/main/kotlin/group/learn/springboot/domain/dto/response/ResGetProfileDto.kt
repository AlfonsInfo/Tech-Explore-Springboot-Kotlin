package group.learn.springboot.domain.dto.response

import java.util.*

data class ResGetProfileDto (
    val id : UUID,
    var name : String ? = null,
    var username : String ? = null,
    var email : String ? = null
)