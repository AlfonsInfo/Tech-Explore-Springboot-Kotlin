package group.learn.querymethod.service.employee

import group.learn.querymethod.domain.constant.ExceptionMessage
import group.learn.querymethod.domain.entity.DepartmentEntity
import group.learn.querymethod.domain.entity.EmployeeEntity
import group.learn.querymethod.exception.BadRequest
import group.learn.querymethod.exception.DataNotFound
import group.learn.querymethod.exception.DuplicateDataException
import group.learn.querymethod.repository.DepartmentRepository
import group.learn.querymethod.repository.EmployeeRepository
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import java.util.*

@Validated
@Service
class EmployeeValidationService(
    private val employeeRepository: EmployeeRepository
) {
        fun validateNoDuplicateEmployee(name : String) {
            if(employeeRepository.existsByNameAndFlagDeleted(name)){
                throw DuplicateDataException(ExceptionMessage.DEPARTMENT_DUPLICATE.format(name))
            }
        }

        //Get Or Throw
        fun getEmployeeOrThrow(id: Any?): EmployeeEntity {
            if (id == null) throw BadRequest(ExceptionMessage.REQUEST_ID_CANNOT_NULL)
            val idString = convertIntoUUID(id)
            return employeeRepository.findOneByIdAndFlagDeleted(idString)
                .orElseThrow {throw DataNotFound(ExceptionMessage.DEPARTMENT_NOT_FOUND.format(idString))}
        }

    fun convertIntoUUID(id: Any): UUID = when (id) {
        is String -> UUID.fromString(id)
        is UUID -> id
        else -> throw BadRequest("ID TYPE NOT MATCH")
    }

}