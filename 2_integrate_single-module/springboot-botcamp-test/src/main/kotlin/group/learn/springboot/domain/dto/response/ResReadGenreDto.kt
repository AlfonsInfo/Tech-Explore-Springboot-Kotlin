package group.learn.springboot.domain.dto.response

import com.fasterxml.jackson.annotation.JsonInclude

data class ResReadGenreDto (
    var id : String ? = null,
    var name : String ? = null,
    var desc : String ? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var music : List<ResMusicByGenreDto>? = null
)

data class ResMusicByGenreDto(
    var musicName : String ? = null,
    var type : String ? = null
)