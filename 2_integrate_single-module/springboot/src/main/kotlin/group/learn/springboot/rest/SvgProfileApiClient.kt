package group.learn.springboot.rest

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    value = "svgProfile" ,
    url = "https://api.dicebear.com/8.x/pixel-art/svg")
interface SvgProfileApiClient {

    @RequestMapping(
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_XML_VALUE]
    )
    fun getProfile(
        @RequestParam("seed") seed : String = "male"
    ) : String
}