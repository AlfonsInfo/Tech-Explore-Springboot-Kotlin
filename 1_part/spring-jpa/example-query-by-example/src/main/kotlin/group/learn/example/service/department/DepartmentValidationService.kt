package group.learn.example.service.department

import group.learn.example.domain.constant.ExceptionMessage
import group.learn.example.domain.entity.DepartmentEntity
import group.learn.example.exception.BadRequest
import group.learn.example.exception.DataNotFound
import group.learn.example.exception.DuplicateDataException
import group.learn.example.repository.DepartmentRepository
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import java.util.*

@Validated
@Service
class DepartmentValidationService(
    private val departmentRepository: DepartmentRepository
) {
        fun validateNoDuplicateDepartment(name : String) {
            if(departmentRepository.existsByNameAndFlagDeleted(name)){
                throw DuplicateDataException(ExceptionMessage.DEPARTMENT_DUPLICATE.format(name))
            }
        }

        //Get Or Throw
        fun getDepartmentOrThrow(id: Any?): DepartmentEntity {
            if (id == null) {
                throw BadRequest(ExceptionMessage.REQUEST_ID_CANNOT_NULL)
            }

            val idString = when (id) {
                is String -> UUID.fromString(id)
                is UUID -> id // Jika ID adalah UUID, konversi ke String
                else -> throw BadRequest("ID TYPE NOT MATCH")
            }

            // Mengambil departemen berdasarkan ID
            return departmentRepository.findOneByIdAndFlagDeleted(idString)
                .orElseThrow {
                    throw DataNotFound(ExceptionMessage.DEPARTMENT_NOT_FOUND.format(idString))
                }
        }

}