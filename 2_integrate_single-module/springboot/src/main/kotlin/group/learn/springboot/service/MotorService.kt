package group.learn.springboot.service

import group.learn.springboot.domain.dto.request.ReqUpsertDto
import group.learn.springboot.domain.dto.response.ResGetMotorDto
import group.learn.springboot.domain.dto.response.BaseResponse
import java.util.UUID

interface MotorService {
    fun insert(request : ReqUpsertDto) : BaseResponse<String>
    fun update(id : UUID ,request : ReqUpsertDto) : BaseResponse<String>
    fun detail(id : UUID) : BaseResponse<ResGetMotorDto>
    fun list() : BaseResponse<List<ResGetMotorDto>>
    fun delete(id : UUID) : BaseResponse<String>
}