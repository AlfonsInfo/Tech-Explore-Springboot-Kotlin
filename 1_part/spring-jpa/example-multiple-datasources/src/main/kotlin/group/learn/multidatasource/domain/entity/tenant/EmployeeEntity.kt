package group.learn.multidatasource.domain.entity.tenant

import group.learn.multidatasource.domain.entity.BaseEntity
import group.learn.multidatasource.domain.entity.global.DepartmentEntity
import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "master_employee")
data class EmployeeEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,
    var name: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id", nullable = true)
    var supervisor : EmployeeEntity? = null,

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "department_id", nullable = true)
//    var department : DepartmentEntity? = null
) : BaseEntity()
