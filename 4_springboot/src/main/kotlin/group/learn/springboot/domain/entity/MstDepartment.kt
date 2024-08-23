package group.learn.springboot.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "mst_department")
data class MstDepartment (

    @Column(name= "name" , length = 100)
    var name : String? = null,

    @Column(name= "description", columnDefinition = "TEXT")
    var description : String? = null,

    @Column(name= "is_active")
    var isActive : Boolean? = null,

    @Column(name= "is_deleted")
    var isDeleted : Boolean? = null

) : BaseEntity()