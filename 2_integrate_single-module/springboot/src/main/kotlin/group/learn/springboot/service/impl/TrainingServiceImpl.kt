package group.learn.springboot.service.impl

import group.learn.springboot.domain.dto.request.ReqMathOperationDto
import group.learn.springboot.domain.dto.response.ResMathOperationResultDto
import group.learn.springboot.service.TrainingService
import org.springframework.stereotype.Service

@Service
class TrainingServiceImpl : TrainingService{
    override fun tambah(request: ReqMathOperationDto): ResMathOperationResultDto {
        return ResMathOperationResultDto(request.angka1 + request.angka2)
    }

    override fun kurang(request: ReqMathOperationDto): ResMathOperationResultDto {
        return ResMathOperationResultDto(request.angka1  - request.angka2)
    }

    override fun kali(request: ReqMathOperationDto): ResMathOperationResultDto {
        return ResMathOperationResultDto(request.angka1  * request.angka2)
    }

    override fun bagi(request: ReqMathOperationDto): ResMathOperationResultDto {
        return ResMathOperationResultDto(request.angka1  / request.angka2)
    }
}