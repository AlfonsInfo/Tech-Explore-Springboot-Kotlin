package group.learn.springboot.service

import group.learn.springboot.domain.dto.request.ReqMathOperationDto
import group.learn.springboot.domain.dto.response.ResMathOperationResultDto
import group.learn.springboot.domain.dto.response.ResPersonBaseDto
import group.learn.springboot.domain.dto.response.ResPersonWithAgeDto
import group.learn.springboot.domain.dto.response.ResPersonWithRoleDto
import org.springframework.stereotype.Service


interface TrainingService {
    fun tambah(request : ReqMathOperationDto) : ResMathOperationResultDto
    fun kurang(request : ReqMathOperationDto) : ResMathOperationResultDto
    fun kali(request : ReqMathOperationDto) : ResMathOperationResultDto
    fun bagi(request : ReqMathOperationDto) : ResMathOperationResultDto

}