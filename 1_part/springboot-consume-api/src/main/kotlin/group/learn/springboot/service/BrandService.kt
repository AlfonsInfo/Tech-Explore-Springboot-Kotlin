package group.learn.springboot.service

import group.learn.springboot.domain.dto.request.ReqBrandListDto
import group.learn.springboot.domain.dto.response.ResBrandListDto

interface BrandService {

    fun getListBrand(request : ReqBrandListDto) : ResBrandListDto
}