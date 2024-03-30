package group.learn.springboot.service

import group.learn.springboot.domain.dto.request.ReqUpsertDto
import group.learn.springboot.domain.dto.response.ResGetMotorDto
import group.learn.springboot.domain.dto.response.ResMessageDto
import java.util.*

interface MotorServiceManagerService {
    fun saveMotor(request : ReqUpsertDto) : ResMessageDto<String>
}