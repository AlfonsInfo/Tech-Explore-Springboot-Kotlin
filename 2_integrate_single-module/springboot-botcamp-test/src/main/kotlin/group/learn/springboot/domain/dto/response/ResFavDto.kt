package group.learn.springboot.domain.dto.response

import jakarta.persistence.Column

data class ResFavDto (
    var idMusic : String ? = null,
    var userAdded : String ? = null,
)

data class ResFavSingleMusicDto(
    var music : String ? = null
)