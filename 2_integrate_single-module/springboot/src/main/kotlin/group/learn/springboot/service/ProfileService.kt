package group.learn.springboot.service

import group.learn.springboot.domain.dto.request.ReqUpsertProfileDto
import group.learn.springboot.domain.dto.response.ResGetProfileDto
import group.learn.springboot.domain.dto.response.BaseResponse
import java.util.*

interface ProfileService {
    fun insert(request : ReqUpsertProfileDto) : BaseResponse<String>
    fun update(id : UUID, request : ReqUpsertProfileDto) : BaseResponse<String>
    fun detail(id : UUID) : BaseResponse<ResGetProfileDto>
    fun list() : BaseResponse<List<ResGetProfileDto>>
    fun delete(id : UUID) : BaseResponse<String>
}