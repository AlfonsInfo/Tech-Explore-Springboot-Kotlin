package group.learn.querymethod.controller

import group.learn.querymethod.domain.dto.request.ReqUpsertEmployeeDto
import group.learn.querymethod.domain.dto.response.ResAllDetailEmployeeDto
import group.learn.querymethod.domain.dto.response.ResAllEmployeeDto
import group.learn.querymethod.domain.dto.response.ResListEmployeeDto
import group.learn.querymethod.domain.dto.response.ResMessageDto
import group.learn.querymethod.service.employee.EmployeeService
import group.learn.querymethod.service.employeequery.EmployeeQueryService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
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

//
//    @GetMapping("/get-list")
//    fun getList(
//        @ModelAttribute request : ReqGetListDto
//    ) : ResponseEntity<ResMessageDto<List<ResGetListDepartmentDto>>>{
//        val response = employeeService.getList(request)
//        return ResponseEntity.ok(response)
//    }
//
//    @GetMapping("/get-all")
//    fun getAll() : ResponseEntity<ResMessageDto<List<ResGetAllDepartmentDto>>>{
//        val response = employeeService.getAll()
//        return ResponseEntity.ok(response)
//    }
//
//    @PutMapping("/update")
//    fun update(
//        @RequestBody @Valid
//        request : ReqUpsertEmployeeDto
//    ): ResponseEntity<ResMessageDto<Any>>{
//        val response = employeeService.update(request)
//        return ResponseEntity.ok(response)
//    }
//
//    @GetMapping("/get-ancestors")
//    fun getAncestor(
//        @RequestParam id : UUID
//    ) : ResponseEntity<ResMessageDto<List<ResGetAncestorDepartmentDto>>>{
//        val response = employeeService.getAncestor(id)
//        return ResponseEntity.ok(response)
//    }

//    @GetMapping("/get-department-hierarchy")

}