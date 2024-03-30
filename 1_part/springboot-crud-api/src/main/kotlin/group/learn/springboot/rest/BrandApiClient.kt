package group.learn.springboot.rest

import feign.Headers
import group.learn.springboot.domain.dto.request.ReqBrandApiDto
import group.learn.springboot.domain.dto.response.ResBrandApiDto
import group.learn.springboot.domain.dto.response.ResBrandListDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    value = "BrandApi" ,
    url = "https://apidev.acc.co.id/restv2",
    )
interface BrandApiClient {



    @PostMapping("/accgrape/getdata/getbranch")
    fun getList(
        //@RequestBody data: Map<String, Map<String, String>>,
        @RequestBody body : ReqBrandApiDto,
        @RequestHeader("APIKey") apiKey: String = "1234567890",
        @RequestHeader("X-Content-Type-Options") contentTypeOptions: String = "nosniff",
        @RequestHeader("X-XSS-Protection") xssProtection: String ="1; mode=block",
        @RequestHeader("Strict-Transport-Security") transportSecurity: String = "max-age=31536000; includeSubDomains; preload",
        @RequestHeader("X-Frame-Options") frameOptions: String = "SAMEORIGIN",
    ) : ResBrandApiDto
}
