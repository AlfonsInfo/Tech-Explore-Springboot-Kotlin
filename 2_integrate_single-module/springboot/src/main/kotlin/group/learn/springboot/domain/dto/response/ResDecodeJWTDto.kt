package group.learn.springboot.domain.dto.response

data class ResDecodeJWTDto (
    val id : String,
    val email : String,
    val password : String,
    val role : String
)

data class ResDecodeJwtDtoProfile(
    val id : String,
    val name : String,
    val username : String,
    val email : String
)