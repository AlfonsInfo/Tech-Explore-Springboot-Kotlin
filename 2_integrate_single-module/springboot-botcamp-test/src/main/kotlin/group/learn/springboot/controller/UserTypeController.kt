package group.learn.springboot.controller

import group.learn.springboot.constants.KeyValueConstants
import group.learn.springboot.domain.dto.request.ReqReadDto
import group.learn.springboot.domain.dto.request.ReqUpsertUserTypeDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.domain.dto.response.ResReadUserTypeDto
import group.learn.springboot.domain.entity.UserTypeEntity
import group.learn.springboot.domain.repository.UserTypeRepository
import group.learn.springboot.service.UserTypeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/user-type")
class UserTypeController (
    private val userTypeService: UserTypeService,
    private val userTypeRepository: UserTypeRepository
){
    @PostMapping("/dummy")
    fun insertDummy() : ResponseEntity<BaseResponse<String>> {
        val user1 = UserTypeEntity(name = "T001")
        val user2 = UserTypeEntity(name = "T002")
        userTypeRepository.save(user1)
        userTypeRepository.save(user2)
        return ResponseEntity.ok(BaseResponse())
    }

    @PostMapping
    fun create(
        @RequestBody request : ReqUpsertUserTypeDto
    ) : ResponseEntity<BaseResponse<String>>{
        val response = userTypeService.create(request)
        return ResponseEntity.ok(response)
    }

    //* read
    @GetMapping
    fun read(
        @RequestParam(required = false) mode : String?
    ) : ResponseEntity<BaseResponse<List<ResReadUserTypeDto>>>{
        //prepare request
        val request = ReqReadDto(
            mode = mode ?: KeyValueConstants.MODE_VALUE_CONCISE
        )

        //hit service
        val response = userTypeService.read(request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun readDetail(
        @PathVariable(name = "id") id : String,
        @RequestParam(required = false) mode : String?
    ) : ResponseEntity<BaseResponse<ResReadUserTypeDto>>{
        val request = ReqReadDto(
            mode = mode ?: KeyValueConstants.MODE_VALUE_CONCISE
        )

        val response = userTypeService.readDetail(id, request)
        return ResponseEntity.ok(response)
    }



    @PutMapping("/{id}")
    fun update(
        @PathVariable(name = "id") id : String,
        @RequestBody request : ReqUpsertUserTypeDto
    ) : ResponseEntity<BaseResponse<String>>{
        val response = userTypeService.update(id, request)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable(name = "id") id : String,
    ) : ResponseEntity<BaseResponse<String>>{
        val response = userTypeService.delete(id)
        return ResponseEntity.ok(response)
    }
}