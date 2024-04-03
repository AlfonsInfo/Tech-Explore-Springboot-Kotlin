package group.learn.springboot.service

import group.learn.springboot.domain.dto.request.ReqReadDto
import group.learn.springboot.domain.dto.request.ReqUpsertFavDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.domain.dto.response.ResFavDto
import group.learn.springboot.domain.dto.response.ResFavSingleMusicDto
import jakarta.servlet.http.HttpServletRequest

interface FavouriteService {
    fun create(request : ReqUpsertFavDto, httpServletRequest: HttpServletRequest) : BaseResponse<String>

    fun readByUser(httpServletRequest: HttpServletRequest) : BaseResponse<List<ResFavSingleMusicDto>>
    fun read(request : ReqReadDto,  httpServletRequest: HttpServletRequest) : BaseResponse<List<ResFavDto>>
    fun readDetail(id : String, request: ReqReadDto) : BaseResponse<ResFavDto>

//    fun update(id : String , request : ReqUpsertFavDto, httpServletRequest: HttpServletRequest) : BaseResponse<String>

//    fun delete(id : String) : BaseResponse<String>
}