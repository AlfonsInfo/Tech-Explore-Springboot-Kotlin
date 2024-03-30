package group.learn.springboot.controller

import group.learn.springboot.domain.dto.request.ReqMathOperationDto
import group.learn.springboot.domain.dto.response.ResMathOperationResultDto
import group.learn.springboot.service.TrainingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/api")
class TrainingController (
    private val trainingService: TrainingService
){
    @PostMapping("/tambah")
    fun tambah(@RequestBody request : ReqMathOperationDto) : ResponseEntity<ResMathOperationResultDto> {
        val response =  trainingService.tambah(request)
        return  ResponseEntity.ok().body(response)
    }

    @PostMapping("/kurang")
    fun kurang(@RequestBody request : ReqMathOperationDto) : ResponseEntity<ResMathOperationResultDto> {
        val response =  trainingService.kurang(request)
        return  ResponseEntity.ok().body(response)
    }

    @PostMapping("/kali")
    fun kali(@RequestBody request : ReqMathOperationDto) : ResponseEntity<ResMathOperationResultDto> {
        val response =  trainingService.kali(request)
        return  ResponseEntity.ok().body(response)
    }

    @PostMapping("/bagi")
    fun bagi(@RequestBody request : ReqMathOperationDto) : ResponseEntity<ResMathOperationResultDto> {
        val response =  trainingService.bagi(request)
        return  ResponseEntity.ok().body(response)
    }

}