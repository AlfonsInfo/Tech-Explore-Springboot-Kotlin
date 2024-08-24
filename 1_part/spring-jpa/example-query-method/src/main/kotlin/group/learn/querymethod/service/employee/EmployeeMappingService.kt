package group.learn.querymethod.service.employee

import group.learn.querymethod.domain.dto.response.ResDetailDepartmentEmployeeDto
import group.learn.querymethod.domain.dto.response.ResListDepartmentDto
import group.learn.querymethod.domain.entity.DepartmentEntity
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