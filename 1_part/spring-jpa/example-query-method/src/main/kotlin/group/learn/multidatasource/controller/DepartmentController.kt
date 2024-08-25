package group.learn.multidatasource.controller

import group.learn.multidatasource.domain.dto.request.ReqGetListDto
import group.learn.multidatasource.domain.dto.request.ReqUpsertDepartmentDto
import group.learn.multidatasource.domain.dto.response.*
import group.learn.multidatasource.service.department.DepartmentService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/department")
class DepartmentController(
    val departmentService: DepartmentService
) {

    @PostMapping("/create")
    fun create(
        @RequestBody @Valid request : ReqUpsertDepartmentDto
    ): ResponseEntity<ResMessageDto<Any>>{
        val response = departmentService.create(request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/get-list")
    fun getList(
        @ModelAttribute request : ReqGetListDto
    ) : ResponseEntity<ResMessageDto<List<ResListDepartmentDto>>>{
        val response = departmentService.getList(request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/get-all")
    fun getAll() : ResponseEntity<ResMessageDto<List<ResAllDepartmentDto>>>{
        val response = departmentService.getAll()
        return ResponseEntity.ok(response)
    }



    @GetMapping("/get-all-projection")
    fun getAllProjection() : ResponseEntity<ResMessageDto<List<ResAllDepartmentDto>>>{
        val response = departmentService.getAllProjection()
        return ResponseEntity.ok(response)
    }


    @PutMapping("/update")
    fun update(
        @RequestBody @Valid
        request : ReqUpsertDepartmentDto
    ): ResponseEntity<ResMessageDto<Any>>{
        val response = departmentService.update(request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/get-ancestors")
    fun getAncestor(
        @RequestParam id : UUID
    ) : ResponseEntity<ResMessageDto<List<ResAncestorDepartmentDto>>>{
        val response = departmentService.getAncestor(id)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/get-detail-employee-query-recursive")
    fun getDetailEmployeeQueryRecursive(
        @RequestParam id : UUID
    ) : ResponseEntity<ResMessageDto<List<ResAncestorDepartmentDto>>>{
        val response = departmentService.getAncestor(id)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/get-detail-employee-logically")
    fun getDetailEmployeeLogically(
        @RequestParam id : UUID
    ) : ResponseEntity<ResMessageDto<List<ResAncestorDepartmentDto>>>{
        val response = departmentService.getAncestor(id)
        return ResponseEntity.ok(response)
    }
}