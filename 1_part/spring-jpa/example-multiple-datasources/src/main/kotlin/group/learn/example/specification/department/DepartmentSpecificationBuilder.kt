package group.learn.example.specification.department

import group.learn.example.common.extensions.toDateRange
import group.learn.example.domain.dto.request.ReqGetListDto
import group.learn.example.domain.entity.global.DepartmentEntity
import group.learn.example.specification.BaseSpecification
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