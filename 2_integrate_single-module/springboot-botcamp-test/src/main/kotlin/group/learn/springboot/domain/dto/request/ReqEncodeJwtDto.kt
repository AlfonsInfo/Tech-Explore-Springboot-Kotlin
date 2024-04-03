package group.learn.springboot.domain.dto.request

import group.learn.springboot.domain.entity.UserTypeEntity
import jakarta.persistence.*
import java.util.*

data class ReqEncodeJwtDto (

    var id : String? = null,
    var username : String ? = null,
    var email : String ? = null,
    var type : String ? = null,
    var typeId : String ? = null
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_type", referencedColumnName = "id_type")
//    var type: UserTypeEntity? = null
)