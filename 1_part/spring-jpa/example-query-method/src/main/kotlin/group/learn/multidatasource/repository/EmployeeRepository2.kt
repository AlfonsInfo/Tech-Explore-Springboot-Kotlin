package group.learn.multidatasource.repository

import group.learn.multidatasource.domain.entity.EmployeeEntity
import group.learn.multidatasource.domain.entity.EmployeeEntity2
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface EmployeeRepository2 : JpaRepository<EmployeeEntity2, UUID>, JpaSpecificationExecutor<EmployeeEntity2> {
    //exists
    fun existsByNameAndFlagDeleted(name : String, deleted : Boolean = false) : Boolean

    //find
    fun findOneByIdAndFlagActive(id : UUID, boolean: Boolean = true) : Optional<EmployeeEntity>
    fun findOneByIdAndFlagActiveAndFlagDeleted(id : UUID, flagActive: Boolean = true, flagDeleted: Boolean = false) : Optional<EmployeeEntity>
    fun findOneByIdAndFlagDeleted(id : UUID, flagDeleted: Boolean = false) : Optional<EmployeeEntity>

    fun findAllByFlagActiveAndFlagDeleted(active : Boolean = true, deleted : Boolean = false, sort : Sort) : List<EmployeeEntity>

}

