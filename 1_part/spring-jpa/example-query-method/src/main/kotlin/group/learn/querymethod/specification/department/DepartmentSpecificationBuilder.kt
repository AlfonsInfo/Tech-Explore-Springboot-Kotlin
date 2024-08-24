package group.learn.querymethod.specification.department

import group.learn.querymethod.common.extensions.toDateRange
import group.learn.querymethod.domain.dto.request.ReqGetListDto
import group.learn.querymethod.domain.entity.DepartmentEntity
import group.learn.querymethod.specification.BaseSpecification
import org.springframework.data.jpa.domain.Specification

class DepartmentSpecificationBuilder {

    companion object{
        fun createSpecification(request : ReqGetListDto ): Specification<DepartmentEntity> {
            var spec = Specification.where(BaseSpecification.isNotDeleted<DepartmentEntity>())

            request.search?.let { spec = spec.and(DepartmentSpecification.hasNameLike(it)) }
            request.dateRange?.let {
                val (startDate, endDate) = it.toDateRange()
                spec = spec.and(DepartmentSpecification.createdDateBetween(startDate, endDate))
            }
            request.status?.let {
                spec = spec.and(DepartmentSpecification.status(it))
            }
            request.search?.let {
                spec = spec.and(DepartmentSpecification.hasNameLikeCaseInSensitive(request.search))
            }
            return spec
        }
    }

}