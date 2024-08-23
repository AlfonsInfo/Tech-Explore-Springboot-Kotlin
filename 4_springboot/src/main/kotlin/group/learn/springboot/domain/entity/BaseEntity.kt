package group.learn.springboot.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.sql.Timestamp
import java.util.UUID

@MappedSuperclass
abstract class BaseEntity : Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    open var id : UUID? = null

    @Column(name = "created_by", length = 100)
    open var createdBy : String? = null

    @Column(name = "updated_by" , length = 100)
    open var updatedBy : String?  = null


    @Column(name = "deleted_by", length = 100)
    open var deletedBy : String?  = null


    @field:CreationTimestamp
    @Column(name = "created_at")
    open var createdAt: Timestamp? = null

    @field:UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: Timestamp? = null


    @Column(name = "updated_at")
    val deletedAt: Timestamp? = null

}