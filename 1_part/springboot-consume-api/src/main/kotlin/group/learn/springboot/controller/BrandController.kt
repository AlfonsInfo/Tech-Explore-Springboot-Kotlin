package group.learn.springboot.controller

import group.learn.springboot.domain.dto.request.ReqBrandListDto
import group.learn.springboot.domain.dto.response.ResBrandListDto
import group.learn.springboot.service.BrandService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/brand")
class BrandController(
    val brandService: BrandService
) {
    @GetMapping
    fun getListBrand(
        @RequestParam(name = "search", required = false)  search : String = "",
        @RequestParam(name = "limit")   limit  : String
    ) : ResponseEntity<ResBrandListDto>{
        val request = ReqBrandListDto(search = search, limit = limit)
        val response = brandService.getListBrand(request)
        return ResponseEntity.ok(response)
    }

}