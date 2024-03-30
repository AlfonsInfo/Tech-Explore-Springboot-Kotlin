package group.learn.springboot.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ResBrandListDto(
    val data : List<ResBrandDto>
)

data class ResBrandDto(
    @JsonProperty("CD_SP")
    val cdSp : String,
    @JsonProperty("AREA")
    val area : String
)