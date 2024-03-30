package group.learn.springboot.domain.dto.response

data class ResPersonBaseDto (
    val firstname : String,
    val lastname : String
)

data class ResPersonWithAgeDto(val firstname: String, val lastname: String, val age :String)

data class ResPersonWithRoleDto(val firstname: String, val lastname: String, val role :String)