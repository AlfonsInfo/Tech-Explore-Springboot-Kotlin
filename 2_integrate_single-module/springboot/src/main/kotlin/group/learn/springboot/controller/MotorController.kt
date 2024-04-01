package group.learn.springboot.controller

import group.learn.springboot.domain.dto.request.ReqUpsertDto
import group.learn.springboot.domain.dto.response.ResGetMotorDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.service.MotorService
import group.learn.springboot.service.MotorServiceManagerService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping( "/api/v1/crud")
class MotorController (
    val motorService: MotorService,
    val motorServiceManagerService: MotorServiceManagerService
){
    @PostMapping
    fun create(@Valid  @RequestBody request : ReqUpsertDto) : ResponseEntity<BaseResponse<String>>{
        val response = motorService.insert(request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/entity-manager")
    fun createWithEntityManager(@Valid @RequestBody request : ReqUpsertDto) : ResponseEntity<BaseResponse<String>>{
        val response = motorServiceManagerService.saveMotor(request)
        return ResponseEntity.ok(response)
    }


    @PutMapping
    fun update(
        @RequestParam uuid  : UUID,
        @Valid @RequestBody request : ReqUpsertDto) : ResponseEntity<BaseResponse<String>>{
        val response = motorService.update(uuid,request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/detail")
    fun detail(@RequestParam uuid  : UUID) : ResponseEntity<BaseResponse<ResGetMotorDto>>{
        val response = motorService.detail(uuid)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/list")
    fun list() : ResponseEntity<BaseResponse<List<ResGetMotorDto>>>{
        val response = motorService.list()
        return ResponseEntity.ok(response)
    }

    @DeleteMapping
    fun delete(@RequestParam uuid : UUID) : ResponseEntity<BaseResponse<String>>{
        val response = motorService.delete(uuid)
        return ResponseEntity.ok(response)
    }



}