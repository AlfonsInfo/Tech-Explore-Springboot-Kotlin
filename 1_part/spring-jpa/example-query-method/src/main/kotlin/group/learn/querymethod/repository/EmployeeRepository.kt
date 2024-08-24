package group.learn.querymethod.repository

import group.learn.querymethod.domain.entity.EmployeeEntity
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface EmployeeRepository : JpaRepository<EmployeeEntity, UUID>, JpaSpecificationExecutor<EmployeeEntity> {
    //exists
    fun existsByNameAndFlagDeleted(name : String, deleted : Boolean = false) : Boolean

    //find
    fun findOneByIdAndFlagActive(id : UUID, boolean: Boolean = true) : Optional<EmployeeEntity>
    fun findOneByIdAndFlagActiveAndFlagDeleted(id : UUID, flagActive: Boolean = true, flagDeleted: Boolean = false) : Optional<EmployeeEntity>
    fun findOneByIdAndFlagDeleted(id : UUID, flagDeleted: Boolean = false) : Optional<EmployeeEntity>

    fun findAllByFlagActiveAndFlagDeleted(active : Boolean = true, deleted : Boolean = false, sort : Sort) : List<EmployeeEntity>

}

