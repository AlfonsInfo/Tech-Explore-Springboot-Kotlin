package group.learn.springboot.service.impl

import group.learn.springboot.domain.dto.response.ResDogListDto
import group.learn.springboot.domain.dto.response.ResRandomImageDto
import group.learn.springboot.rest.DogApiClient
import group.learn.springboot.service.DogService
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class DogServiceImpl (
    private val dogApiClient: DogApiClient,
    private val restTemplate: RestTemplate
) : DogService{
    override fun getDogImage(): ResRandomImageDto {
        return dogApiClient.getRandomImage()
    }

    override fun getDogImageRestClient(): ResDogListDto? {
        val response : ResDogListDto = restTemplate.getForObject(
            "https://dog.ceo/api/breeds/list/all",
            ResDogListDto::class
        )

        return response.let { ResDogListDto(it.message, it.status) }
    }


}