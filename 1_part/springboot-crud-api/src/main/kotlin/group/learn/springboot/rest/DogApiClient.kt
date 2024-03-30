package group.learn.springboot.rest

import group.learn.springboot.domain.dto.response.ResBrandListDto
import group.learn.springboot.domain.dto.response.ResDogListDto
import group.learn.springboot.domain.dto.response.ResRandomImageDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(value = "DogApi" , url = "https://apidev.acc.co.id/restv2")
interface DogApiClient {
    @GetMapping("/accgrape/getdata/getbranch")
    fun getRandomImage() : ResRandomImageDto

}