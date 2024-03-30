package group.learn.springboot.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ResBrandApiDto (
    @JsonProperty(value = "OUT_STAT") val outStat : String,
    @JsonProperty(value = "OUT_MESS") val outMess : String,
    @JsonProperty(value = "OUT_DATA") val outData : List<ResBrandDto>
)

data class ResBrandApiSingleDto(
    @JsonProperty("CD_SP")
    val cdSp : String,
    @JsonProperty("AREA")
    val area : String
)