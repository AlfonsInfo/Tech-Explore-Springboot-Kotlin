package group.learn.springboot.domain.dto.request

data class ReqEncodeJwtDto(
    val id : String ="",
    val name : String = "",
    val email : String = "",
    val username : String = "",
    val role : String = "",
    val password : String = ""
)
