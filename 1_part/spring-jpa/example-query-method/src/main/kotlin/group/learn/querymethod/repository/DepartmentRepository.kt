package group.learn.querymethod.repository

import group.learn.querymethod.domain.dto.response.ResAllDepartmentDto
import group.learn.querymethod.domain.entity.DepartmentEntity
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface DepartmentRepository : JpaRepository<DepartmentEntity, UUID>, JpaSpecificationExecutor<DepartmentEntity> {
    //exists
    fun existsByNameAndFlagDeleted(name : String, deleted : Boolean = false) : Boolean

    //find
    fun findOneByIdAndFlagActive(id : UUID, boolean: Boolean = true) : Optional<DepartmentEntity>
    fun findOneByIdAndFlagActiveAndFlagDeleted(id : UUID, flagActive: Boolean = true, flagDeleted: Boolean = false) : Optional<DepartmentEntity>
    fun findOneByIdAndFlagDeleted(id : UUID, flagDeleted: Boolean = false) : Optional<DepartmentEntity>

    fun findAllByFlagActiveAndFlagDeleted(active : Boolean = true, deleted : Boolean = false, sort : Sort?) : List<DepartmentEntity>


    // HQL
    @Query("SELECT new group.learn.querymethod.domain.dto.response.ResAllDepartmentDto(d.id.toString(),d.name) from DepartmentEntity d")
    fun findAllProjection (sort : Sort) : List<ResAllDepartmentDto>
}

