package group.learn.multidatasource.service.employee

import group.learn.multidatasource.domain.constant.ExceptionMessage
import group.learn.multidatasource.domain.entity.tenant.EmployeeEntity
import group.learn.multidatasource.exception.BadRequest
import group.learn.multidatasource.exception.DataNotFound
import group.learn.multidatasource.exception.DuplicateDataException
import group.learn.multidatasource.repository.tenant.EmployeeRepository
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