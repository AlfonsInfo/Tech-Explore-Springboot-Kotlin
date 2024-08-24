package group.learn.querymethod.specification

import group.learn.querymethod.domain.entity.BaseEntity
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification


abstract class BaseSpecification {

    companion object {
        fun <T : BaseEntity> isActive(): Specification<T> {
            return Specification<T> { root: Root<T>, _: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder ->
                criteriaBuilder.isTrue(root["flagActive"])
            }
        }

        fun <T : BaseEntity> isNonActive(): Specification<T> {
            return Specification<T> { root: Root<T>, _: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder ->
                criteriaBuilder.isFalse(root["flagActive"])
            }
        }

        fun <T : BaseEntity> isNotDeleted(): Specification<T> {
            return Specification<T> { root: Root<T>, _: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder ->
                criteriaBuilder.isFalse(root["flagDeleted"])
            }
        }

    }
}