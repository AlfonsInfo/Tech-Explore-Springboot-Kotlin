package group.learn.example.service.employee

import group.learn.example.domain.constant.ExceptionMessage
import group.learn.example.domain.entity.tenant.EmployeeEntity
import group.learn.example.exception.BadRequest
import group.learn.example.exception.DataNotFound
import group.learn.example.exception.DuplicateDataException
import group.learn.example.repository.tenant.EmployeeRepository
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