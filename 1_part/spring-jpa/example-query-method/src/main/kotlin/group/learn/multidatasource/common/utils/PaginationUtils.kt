package group.learn.multidatasource.common.utils

import group.learn.multidatasource.domain.common.Pagination
import group.learn.multidatasource.domain.dto.request.ReqGetListDto
import org.springframework.data.domain.Page

class PaginationUtils {

    companion object{
        fun <T>createPagination(
            response: Page<T>,
            request: ReqGetListDto,
        ) : Pagination {
            return Pagination(
                totalRecords = response.totalElements.toInt(),
                currentPage = response.number + 1,
                totalPage = response.totalPages,
                nextPage = Pagination.getNextPage(request.page, request.show, response.totalPages),
                prevPage = Pagination.getPrevPage(request.page, request.show)

            )
        }
    }
}