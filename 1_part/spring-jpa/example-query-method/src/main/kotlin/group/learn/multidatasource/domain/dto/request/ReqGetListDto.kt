package group.learn.multidatasource.domain.dto.request

data class ReqGetListDto (
    val search: String?,
    val status: Boolean?,
    val dateRange: String?,  // Format "2023-01-01,2023-12-31"
    val show: Int = 15,
    val page: Int = 1
)