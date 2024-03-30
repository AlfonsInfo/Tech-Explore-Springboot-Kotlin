package group.learn.springboot.service

import group.learn.springboot.domain.dto.response.ResDogListDto
import group.learn.springboot.domain.dto.response.ResRandomImageDto

interface DogService {
    fun getDogImage () : ResRandomImageDto

    fun getDogImageRestClient () : ResDogListDto?

}