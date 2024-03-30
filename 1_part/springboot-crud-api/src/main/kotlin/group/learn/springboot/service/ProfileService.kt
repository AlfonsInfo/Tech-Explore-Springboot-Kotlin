package group.learn.springboot.service

import group.learn.springboot.domain.dto.request.ReqUpsertProfileDto
import group.learn.springboot.domain.dto.response.ResGetProfileDto
import group.learn.springboot.domain.dto.response.ResMessageDto
import java.util.*

interface ProfileService {
    fun insert(request : ReqUpsertProfileDto) : ResMessageDto<String>
    fun update(id : UUID, request : ReqUpsertProfileDto) : ResMessageDto<String>
    fun detail(id : UUID) : ResMessageDto<ResGetProfileDto>
    fun list() : ResMessageDto<List<ResGetProfileDto>>
    fun delete(id : UUID) : ResMessageDto<String>
}