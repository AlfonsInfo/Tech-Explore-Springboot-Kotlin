package group.learn.springboot.service

import group.learn.springboot.domain.dto.request.ReqUpsertDto
import group.learn.springboot.domain.dto.response.ResGetMotorDto
import group.learn.springboot.domain.dto.response.ResMessageDto
import java.util.UUID

interface MotorService {
    fun insert(request : ReqUpsertDto) : ResMessageDto<String>
    fun update(id : UUID ,request : ReqUpsertDto) : ResMessageDto<String>
    fun detail(id : UUID) : ResMessageDto<ResGetMotorDto>
    fun list() : ResMessageDto<List<ResGetMotorDto>>
    fun delete(id : UUID) : ResMessageDto<String>
}