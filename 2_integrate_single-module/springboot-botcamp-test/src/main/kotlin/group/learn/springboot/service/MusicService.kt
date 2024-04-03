package group.learn.springboot.service

import group.learn.springboot.domain.dto.request.ReqReadDto
import group.learn.springboot.domain.dto.request.ReqUpsertGenreDto
import group.learn.springboot.domain.dto.request.ReqUpsertMusicDto
import group.learn.springboot.domain.dto.request.ReqUpsertUserTypeDto
import group.learn.springboot.domain.dto.response.*
import jakarta.servlet.http.HttpServletRequest


interface MusicService {
    fun create(request : ReqUpsertMusicDto) : BaseResponse<String>

    fun read( request : ReqReadDto, httpServletRequest: HttpServletRequest) : BaseResponse<List<ResMusicDto>>
    fun readDetail(id : String, request: ReqReadDto) : BaseResponse<ResMusicDto>

    fun update(id : String , request : ReqUpsertMusicDto) : BaseResponse<String>

    fun delete(id : String) : BaseResponse<String>
}