package group.learn.example.domain.entity

import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "master_employee_bulk_data_test")
data class EmployeeEntity2 (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,
    var name: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id", nullable = true)
    var supervisor : EmployeeEntity2? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = true)
    var department : DepartmentEntity? = null
) : BaseEntity()
