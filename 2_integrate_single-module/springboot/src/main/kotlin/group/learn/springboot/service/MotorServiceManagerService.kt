package group.learn.springboot.service

import group.learn.springboot.domain.dto.request.ReqUpsertDto
import group.learn.springboot.domain.dto.response.BaseResponse

interface MotorServiceManagerService {
    fun saveMotor(request : ReqUpsertDto) : BaseResponse<String>
}