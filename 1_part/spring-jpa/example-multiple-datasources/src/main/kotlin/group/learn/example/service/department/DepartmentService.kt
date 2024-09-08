package group.learn.example.service.department

import group.learn.example.common.utils.PaginationUtils.Companion.createPagination
import group.learn.example.domain.dto.request.ReqGetListDto
import group.learn.example.domain.dto.request.ReqUpsertDepartmentDto
import group.learn.example.domain.dto.response.*
import group.learn.example.domain.entity.global.DepartmentEntity
import group.learn.example.repository.global.DepartmentRepository
import group.learn.example.specification.department.DepartmentSpecificationBuilder
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DepartmentService(
    private val departmentRepository: DepartmentRepository,
    private val departmentValidationService: DepartmentValidationService
) {

    fun create(request : ReqUpsertDepartmentDto) : ResMessageDto<Any> {
        val departmentEntity = DepartmentEntity()
        departmentValidationService.validateNoDuplicateDepartment(request.name!!)
        val departmentParent =  request.departmentParent?.let { departmentValidationService.getDepartmentOrThrow(request.departmentParent) }

        departmentEntity.name = request.name
        departmentEntity.parentDept = departmentParent
        departmentRepository.save(departmentEntity)
        return ResMessageDto(message = "successfully create department")
    }

    fun update(request : ReqUpsertDepartmentDto) : ResMessageDto<Any> {
        val departmentEntity = departmentValidationService.getDepartmentOrThrow(request.id)
        val departmentParent =  request.departmentParent?.let { departmentValidationService.getDepartmentOrThrow(request.departmentParent) }

        val isDepartmentNotNullAndNotSameWithOld = request.name != null && request.name != departmentEntity.name
        if(isDepartmentNotNullAndNotSameWithOld) departmentValidationService.validateNoDuplicateDepartment(request.name!!)

        departmentEntity.name = request.name ?: departmentEntity.name
        departmentEntity.flagActive = request.isActive ?: departmentEntity.flagActive
        departmentEntity.parentDept = departmentParent ?: departmentEntity.parentDept

        departmentRepository.save(departmentEntity)
        return ResMessageDto(message = "successfully create department")
    }

    fun getList(request : ReqGetListDto) : ResMessageDto<List<ResListDepartmentDto>> {
        val pageReq = request.page - 1
        val showReq = request.show

        val spec  = DepartmentSpecificationBuilder.createSpecification(request)
        val pageable = PageRequest.of(pageReq, showReq, Sort.by(Sort.Direction.DESC, "updatedAt"))
        val resDepartment = departmentRepository.findAll(spec,pageable)
        val pagination = createPagination(resDepartment, request)
        val mappingResponse = DepartmentMappingService.mapIntoResGetListDepartment(resDepartment)
        return ResMessageDto(data = mappingResponse,pagination = pagination)
    }

    fun getAll() : ResMessageDto<List<ResAllDepartmentDto>> {
        val departmentSort = Sort.by(Sort.Direction.ASC, "name")
        val departmentEntityList = departmentRepository.findAllByFlagActiveAndFlagDeleted(sort = departmentSort)
        val responseData = DepartmentMappingService.mapIntoResGetAllDepartment(departmentEntityList)
        return ResMessageDto(data = responseData )
    }

    fun getAllProjection() : ResMessageDto<List<ResAllDepartmentDto>> {
        val departmentSort = Sort.by(Sort.Direction.ASC, "name")
        val responseData = departmentRepository.findAllProjection(sort = departmentSort)
        return ResMessageDto(data = responseData )
    }

    fun getAncestor(id : UUID) :ResMessageDto<List<ResAncestorDepartmentDto>>{
        val departmentEntity = departmentValidationService.getDepartmentOrThrow(id)
        val listAncestor = mutableListOf<ResAncestorDepartmentDto>()
        setAncestor(departmentEntity, listAncestor)
        return ResMessageDto(data = listAncestor)
    }

    fun getDetailEmployee(id : UUID) : ResMessageDto<ResDetailDepartmentEmployeeDto>{

        return ResMessageDto()
    }



    //Helper Method
    private fun setAncestor(
        departmentEntity: DepartmentEntity,
        listAncestor: MutableList<ResAncestorDepartmentDto>,
    ) {
        var currentDepartment = departmentEntity
        var level = 1
        while (currentDepartment.parentDept != null) {
            currentDepartment.parentDept?.let { parent ->
                // Tambahkan departemen parent ke daftar ancestor
                listAncestor.add(
                    ResAncestorDepartmentDto(
                        id = parent.id.toString(),
                        name = parent.name,
                        level = level
                    )
                )
                currentDepartment = parent
                level++
            }
        }
    }
}