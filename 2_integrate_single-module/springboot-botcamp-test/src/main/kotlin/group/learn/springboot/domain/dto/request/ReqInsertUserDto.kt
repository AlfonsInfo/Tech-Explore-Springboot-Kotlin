package group.learn.springboot.domain.dto.request



data class ReqInsertUserDto (
    var username : String ? = null,
    var email : String ? = null,
    var password : String ? = null,
    var idType : String = "T001"
)