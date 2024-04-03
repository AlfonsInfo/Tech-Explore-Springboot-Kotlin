package group.learn.springboot.domain.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import group.learn.springboot.domain.entity.UserEntity
import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

data class ResReadUserTypeDto (
    var id : String = "",
    var name : String = "",
    var desc : String = "",

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var user : List<ResReadDetailUser>? = null
)


data class ResReadDetailUser(
    var id : UUID? = null,
    var username : String ? = null,
    var email : String ? = null,
)