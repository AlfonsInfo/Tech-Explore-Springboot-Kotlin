package group.learn.example.service.employee

import group.learn.example.domain.dto.request.ReqUpsertEmployeeDto
import group.learn.example.domain.dto.response.ResMessageDto
import group.learn.example.domain.entity.EmployeeEntity
import group.learn.example.repository.EmployeeRepository
import group.learn.example.service.department.DepartmentValidationService
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val departmentValidationService: DepartmentValidationService,
    private val employeeValidationService: EmployeeValidationService
) {

    fun create(request : ReqUpsertEmployeeDto) : ResMessageDto<Any> {
        val employeeEntity = EmployeeEntity()
        employeeValidationService.validateNoDuplicateEmployee(request.name!!)
        val departmentEntity =  request.departmentId?.let { departmentValidationService.getDepartmentOrThrow(request.departmentId) }
        val supervisorEntity = request.supervisorId?.let { employeeValidationService.getEmployeeOrThrow(request.supervisorId) }
        employeeEntity.name = request.name
        employeeEntity.department = departmentEntity
        employeeEntity.supervisor = supervisorEntity

        employeeRepository.save(employeeEntity)
        return ResMessageDto(message = "successfully create employee")
    }

//    fun update(request : ReqUpsertEmployeeDto) : ResMessageDto<Any> {
//        val departmentEntity = employeeValidionService.getDepartmentOrThrow(request.id)
//        val departmentParent =  request.departmentParent?.let { employeeValidionService.getDepartmentOrThrow(request.departmentParent) }
//
//        val isDepartmentNotNullAndNotSameWithOld = request.name != null && request.name != departmentEntity.name
//        if(isDepartmentNotNullAndNotSameWithOld) employeeValidionService.validateNoDuplicateDepartment(request.name!!)
//
//        departmentEntity.name = request.name ?: departmentEntity.name
//        departmentEntity.flagActive = request.isActive ?: departmentEntity.flagActive
//        departmentEntity.parentDept = departmentParent ?: departmentEntity.parentDept
//
//        employeeRepository.save(departmentEntity)
//        return ResMessageDto(message = "successfully create department")
//    }

//    fun getList(request : ReqGetListDto) : ResMessageDto<List<ResGetListDepartmentDto>> {
//        val pageReq = request.page - 1
//        val showReq = request.show
//
//        val spec  = DepartmentSpecificationBuilder.createSpecification(request)
//        val pageable = PageRequest.of(pageReq, showReq, Sort.by(Sort.Direction.DESC, "updatedAt"))
//        val resDepartment = employeeRepository.findAll(spec,pageable)
//        val pagination = createPagination(resDepartment, request)
//        val mappingResponse = EmployeeMappingService.mapIntoResGetListDepartment(resDepartment)
//        return ResMessageDto(data = mappingResponse,pagination = pagination)
//    }

//    fun getAll() : ResMessageDto<List<ResGetAllDepartmentDto>> {
//        val departmentSort = Sort.by(Sort.Direction.ASC, "name")
//        val departmentEntityList = employeeRepository.findAllByFlagActiveAndFlagDeleted(sort = departmentSort)
//        val responseData = EmployeeMappingService.mapIntoResGetAllDepartment(departmentEntityList)
//        return ResMessageDto(data = responseData )
//    }



//    fun getAncestor(id : UUID) :ResMessageDto<List<ResGetAncestorDepartmentDto>>{
//        val departmentEntity = employeeValidionService.getDepartmentOrThrow(id)
//        val listAncestor = mutableListOf<ResGetAncestorDepartmentDto>()
//        setAncestor(departmentEntity, listAncestor)
//        return ResMessageDto(data = listAncestor)
//    }

//    private fun setAncestor(
//        departmentEntity: EmployeeEntity,
//        listAncestor: MutableList<ResGetAncestorDepartmentDto>,
//    ) {
//        var currentDepartment = departmentEntity
//        var level = 1
//        while (currentDepartment.parentDept != null) {
//            currentDepartment.parentDept?.let { parent ->
//                // Tambahkan departemen parent ke daftar ancestor
//                listAncestor.add(
//                    ResGetAncestorDepartmentDto(
//                        id = parent.id.toString(),
//                        name = parent.name,
//                        level = level
//                    )
//                )
//                currentDepartment = parent
//                level++
//            }
//        }
//    }
}