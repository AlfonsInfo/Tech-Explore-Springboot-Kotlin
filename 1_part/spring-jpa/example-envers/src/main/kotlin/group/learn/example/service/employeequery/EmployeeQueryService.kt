package group.learn.example.service.employeequery

import group.learn.example.domain.dto.response.ResAllDetailEmployeeDto
import group.learn.example.domain.dto.response.ResAllEmployeeDto
import group.learn.example.domain.dto.response.ResListEmployeeDto
import group.learn.example.domain.dto.response.ResMessageDto
import group.learn.example.repository.EmployeeRepository
import group.learn.example.repository.EmployeeRepository2
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmployeeQueryService(
    private val employeeRepository2: EmployeeRepository2,
    private val employeeRepository: EmployeeRepository
) {

    fun getAllEmployee() : ResMessageDto<List<ResAllEmployeeDto>>{
        val employeeEntities = employeeRepository2.findAll()
        val response = employeeEntities.map { ResAllEmployeeDto(it.id.toString(), it.name) }
        return ResMessageDto(data = response)

    }

    fun getAllDetailEmployee() : ResMessageDto<List<ResAllDetailEmployeeDto>>{
        val employeeEntities = employeeRepository2.findAll()
        val response = employeeEntities.map { ResAllDetailEmployeeDto(
           id =  it.id.toString(),
           name =  it.name,
            createdBy = getEmployee(it.createdBy!!),
            updatedBy = getEmployee(it.updatedBy!!),
        ) }
        return ResMessageDto(data = response)

    }

    fun getEmployee(createdAt : String): String{
        return employeeRepository.findById(UUID.fromString(createdAt)).get().name ?: ""
    }

    fun getListEmployee() : ResMessageDto<List<ResListEmployeeDto>>{
        val employeeEntities = employeeRepository2.findAll()
        val response = employeeEntities.map { ResListEmployeeDto(it.id.toString(), it.name,it.department?.name) }
        return ResMessageDto(data = response)
    }

}