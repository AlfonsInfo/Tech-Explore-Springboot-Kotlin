package group.learn.multidatasource.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.sql.Timestamp


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity: Serializable {
    @Column(name = "is_active")
    var flagActive: Boolean = true

    @Column(name = "is_deleted")
    var flagDeleted: Boolean = false

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: Timestamp? = null

    @CreatedBy
    @Column(name = "created_by", length = 100)
    var createdBy: String? = null

    @UpdateTimestamp
    @Column(name = "updated_at", updatable = true)
    var updatedAt: Timestamp? = null

    @LastModifiedBy
    @Column(name = "updated_by", length = 100)
    var updatedBy: String? = null

    @Column(name = "deleted_at", updatable = true)
    var deletedAt: Timestamp? = null

    @Column(name = "deleted_by", length = 100)
    var deletedBy: String? = null
    companion object {
        private const val serialVersionUID: Long = 7933094511403619040L
    }
}