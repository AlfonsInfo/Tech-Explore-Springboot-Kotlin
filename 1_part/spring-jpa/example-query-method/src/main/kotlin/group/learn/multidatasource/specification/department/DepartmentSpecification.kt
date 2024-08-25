package group.learn.multidatasource.specification.department


import group.learn.multidatasource.domain.entity.DepartmentEntity
import group.learn.multidatasource.specification.BaseSpecification
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.util.Date


class DepartmentSpecification : BaseSpecification() {

    companion object {

        fun isActive(): Specification<DepartmentEntity> {
            return Specification<DepartmentEntity> { root: Root<DepartmentEntity>, _: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder ->
                criteriaBuilder.isTrue(root["flagActive"])
            }
        }

        fun status(status : Boolean): Specification<DepartmentEntity> {
            return Specification<DepartmentEntity> { root: Root<DepartmentEntity>, _: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder ->
                if (status == null) {
                    return@Specification criteriaBuilder.conjunction()
                }
                if(status){
                    criteriaBuilder.isTrue(root["flagActive"])
                }else{
                    criteriaBuilder.isFalse(root["flagActive"])
                }
            }
        }

        fun isNotDeleted(): Specification<DepartmentEntity> {
            return Specification<DepartmentEntity> { root: Root<DepartmentEntity>, _: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder ->
                criteriaBuilder.isFalse(root["flagDeleted"])
            }
        }

        fun hasNameLike(name: String?): Specification<DepartmentEntity> {
            return Specification<DepartmentEntity> { root: Root<DepartmentEntity>, _: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder ->
                if (name == null) {
                    return@Specification criteriaBuilder.conjunction()
                }
                criteriaBuilder.like(root["name"], "%$name%")
            }
        }

        fun hasNameSame(name: String?): Specification<DepartmentEntity> {
            return Specification<DepartmentEntity> { root: Root<DepartmentEntity>, _: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder ->
                if (name == null) {
                    return@Specification criteriaBuilder.conjunction()
                }
                criteriaBuilder.like(root["name"], "$name")
            }
        }

        fun hasNameLikeCaseInSensitive(name: String?): Specification<DepartmentEntity> {
            return Specification<DepartmentEntity> { root: Root<DepartmentEntity>, _: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder ->
                if (name == null) {
                    return@Specification criteriaBuilder.conjunction()
                }
                criteriaBuilder.like(criteriaBuilder.lower(root["name"]), "%${name.lowercase()}%")
            }
        }

        fun createdDateBetween(startDate: Date?, endDate: Date?): Specification<DepartmentEntity> {
            return Specification<DepartmentEntity> { root: Root<DepartmentEntity>, _: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder ->
                if (startDate == null || endDate == null) {
                    return@Specification criteriaBuilder.conjunction() // No filtering if dates are null
                }

                // Convert Date to LocalDate
                val startDateAsLocalDate = LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault())
                val endDateAsLocalDate = LocalDate.ofInstant(endDate.toInstant(), ZoneId.systemDefault())

                // Convert LocalDate to LocalDateTime (assuming "createdAt" stores datetime)
                val startDateAsLocalDateTime = startDateAsLocalDate.atStartOfDay()
                val endDateAsLocalDateTime = endDateAsLocalDate.atTime(LocalTime.MAX) // Adjust time as needed

                criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), startDateAsLocalDateTime),
                    criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), endDateAsLocalDateTime)
                )
            }
        }
    }
}
