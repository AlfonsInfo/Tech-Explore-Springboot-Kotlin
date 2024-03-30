package group.learn.springboot.service.impl

import group.learn.springboot.domain.dto.request.ReqBrandApiDto
import group.learn.springboot.domain.dto.request.ReqBrandDetailDto
import group.learn.springboot.domain.dto.request.ReqBrandListDto
import group.learn.springboot.domain.dto.response.ResBrandListDto
import group.learn.springboot.rest.BrandApiClient
import group.learn.springboot.service.BrandService
import org.springframework.stereotype.Service

@Service
class BrandServiceImpl(
    val brandApiClient: BrandApiClient
) : BrandService{
    override fun getListBrand(request: ReqBrandListDto): ResBrandListDto {
        val requestDto = ReqBrandApiDto(
            ReqBrandDetailDto(
                pSearch = request.search,
                pLimit = request.limit
            )
        )
        val mapped = mapOf("P_SEARCH" to request.search, "P_LIMIT" to request.limit)
        val fromFeign = brandApiClient.getList(
            body = requestDto ,
          //data = mapOf("doGetBranch" to mapped)
        )
        val mappedData = ResBrandListDto(data = fromFeign.outData)
        return mappedData
    }

}