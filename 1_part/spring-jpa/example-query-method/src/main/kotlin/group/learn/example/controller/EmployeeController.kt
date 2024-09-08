package group.learn.example.controller

import group.learn.example.domain.dto.request.ReqUpsertEmployeeDto
import group.learn.example.domain.dto.response.ResMessageDto
import group.learn.example.service.employee.EmployeeService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/employee")
class EmployeeController(
    val employeeService: EmployeeService
) {

    @PostMapping("/create")
    fun create(
        @RequestBody @Valid request : ReqUpsertEmployeeDto
    ): ResponseEntity<ResMessageDto<Any>>{
        val response = employeeService.create(request)
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