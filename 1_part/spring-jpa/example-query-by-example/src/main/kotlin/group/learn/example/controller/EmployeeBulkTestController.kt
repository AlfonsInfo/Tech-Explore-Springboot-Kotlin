package group.learn.example.controller

import group.learn.example.domain.dto.request.ReqUpsertEmployeeDto
import group.learn.example.domain.dto.response.ResAllDetailEmployeeDto
import group.learn.example.domain.dto.response.ResAllEmployeeDto
import group.learn.example.domain.dto.response.ResListEmployeeDto
import group.learn.example.domain.dto.response.ResMessageDto
import group.learn.example.service.employeequery.EmployeeQueryService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/employee-bulk")
class EmployeeBulkTestController(
    val employeeService: EmployeeQueryService
) {
    @GetMapping("/get-all")
    fun getAll(
        @RequestBody @Valid request : ReqUpsertEmployeeDto
    ): ResponseEntity<ResMessageDto<List<ResAllEmployeeDto>>>{
        val response = employeeService.getAllEmployee()
        return ResponseEntity.ok(response)
    }

    //Explain N+1 Problem
    @GetMapping("/get-all-detail")
    fun getAllDetail(
        @RequestBody @Valid request : ReqUpsertEmployeeDto
    ): ResponseEntity<ResMessageDto<List<ResAllDetailEmployeeDto>>>{
        val response = employeeService.getAllDetailEmployee()
        return ResponseEntity.ok(response)
    }


    @GetMapping("/get-list")
    fun getList(
        @RequestBody @Valid request : ReqUpsertEmployeeDto
    ): ResponseEntity<ResMessageDto<List<ResListEmployeeDto>>>{
        val response = employeeService.getListEmployee()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/search")
    fun getSearch(
        @RequestParam nama : String
    ): ResponseEntity<ResMessageDto<ResAllDetailEmployeeDto>>{
        val response = employeeService.getDetail(nama)
        return ResponseEntity.ok(response)
    }

}