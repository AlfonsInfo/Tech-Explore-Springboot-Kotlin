package group.learn.springboot.service

import group.learn.springboot.domain.dto.request.ReqReadDto
import group.learn.springboot.domain.dto.request.ReqUpsertGenreDto
import group.learn.springboot.domain.dto.request.ReqUpsertUserTypeDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.domain.dto.response.ResReadDetailUser
import group.learn.springboot.domain.dto.response.ResReadGenreDto
import group.learn.springboot.domain.dto.response.ResReadUserTypeDto


interface GenreService {
    fun create(request : ReqUpsertGenreDto) : BaseResponse<String>

    fun read( request : ReqReadDto) : BaseResponse<List<ResReadGenreDto>>
    fun readDetail(id : String, request: ReqReadDto) : BaseResponse<ResReadGenreDto>

    fun update(id : String , request : ReqUpsertGenreDto) : BaseResponse<String>

    fun delete(id : String) : BaseResponse<String>
}