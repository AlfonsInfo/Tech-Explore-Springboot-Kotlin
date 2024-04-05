package group.learn.springboot.domain.dto.response

import com.fasterxml.jackson.annotation.JsonInclude

data class ResMusicDto (
    var id : String? = null,
    var musicName : String ? = null,
    var musicLyric : String ? = null,
    var type : String ? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var genre : List<ResGenreByMusic> ? = null
)



data class ResGenreByMusic(
    var name : String ? = null,
    var desc : String ? = null
)