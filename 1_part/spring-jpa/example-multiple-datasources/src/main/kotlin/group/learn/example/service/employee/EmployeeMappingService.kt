package group.learn.example.service.employee

import group.learn.example.domain.dto.response.ResDetailDepartmentEmployeeDto
import group.learn.example.domain.dto.response.ResListDepartmentDto
import group.learn.example.domain.entity.global.DepartmentEntity
import org.springframework.data.domain.Page

class EmployeeMappingService {
    companion object{
        fun mapIntoResGetAllDepartment(listDepartment : List<DepartmentEntity>) : List<ResDetailDepartmentEmployeeDto>{
            return listDepartment.map { ResDetailDepartmentEmployeeDto(id = it.name, name = it.name) }
        }


        fun mapIntoResGetListDepartment(pageDepartmentEntity : Page<DepartmentEntity>) : List<ResListDepartmentDto>{
            return  pageDepartmentEntity.content.map { ResListDepartmentDto(id = it.id.toString(), name = it.name, isActive = it.flagActive)}
        }


    }
}