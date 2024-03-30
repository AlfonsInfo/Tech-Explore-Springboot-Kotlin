package group.learn.springboot.domain.dto.request


import com.fasterxml.jackson.annotation.JsonProperty

data class ReqBrandApiDto (
    @get:JsonProperty("doGetBranch")
    val doGetBranch : ReqBrandDetailDto
)

//@JsonAutoDetect(fieldVisibility = Visibility.ANY)
data class ReqBrandDetailDto(
    @get:JsonProperty("P_SEARCH") val pSearch : String,
    @get:JsonProperty("P_LIMIT") val pLimit : String
)