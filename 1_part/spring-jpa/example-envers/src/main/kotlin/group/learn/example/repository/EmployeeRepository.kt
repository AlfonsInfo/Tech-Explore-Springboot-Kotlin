package group.learn.example.repository

import group.learn.example.domain.entity.DepartmentEntity
import group.learn.example.domain.entity.EmployeeEntity
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface EmployeeRepository : JpaRepository<EmployeeEntity, UUID>, JpaSpecificationExecutor<EmployeeEntity> {
    //exists
    fun existsByNameAndFlagDeleted(name : String, deleted : Boolean = false) : Boolean

    //find
    fun findOneByIdAndFlagActive(id : UUID, boolean: Boolean = true) : Optional<EmployeeEntity>
    fun findOneByIdAndFlagActiveAndFlagDeleted(id : UUID, flagActive: Boolean = true, flagDeleted: Boolean = false) : Optional<EmployeeEntity>
    fun findOneByIdAndFlagDeleted(id : UUID, flagDeleted: Boolean = false) : Optional<EmployeeEntity>

    fun findAllByFlagActiveAndFlagDeleted(active : Boolean = true, deleted : Boolean = false, sort : Sort) : List<EmployeeEntity>


    @Query("""
        WITH RECURSIVE Ancestors AS (
            SELECT
                id,
                name,
                parent_id
            FROM
                master_department
            WHERE
                id = :id

            UNION ALL

            SELECT
                d.id,
                d.name,
                d.parent_id
            FROM
                master_department d
            INNER JOIN
                Ancestors a
            ON
                d.id = a.parent_id
        )
        SELECT * FROM Ancestors
    """, nativeQuery = true)
    fun findAncestors(@Param("id") id: UUID): List<DepartmentEntity>

    @Query("""
        WITH RECURSIVE Descendants AS (
            SELECT
                id,
                name,
                parent_id
            FROM
                master_department
            WHERE
                id = :id

            UNION ALL

            SELECT
                d.id,
                d.name,
                d.parent_id
            FROM
                master_department d
            INNER JOIN
                Descendants dc
            ON
                d.parent_id = dc.id
        )
        SELECT * FROM Descendants
    """, nativeQuery = true)
    fun findDescendants(@Param("id") id: UUID): List<DepartmentEntity>
}

