package group.learn.springboot.domain.dto.request

import group.learn.springboot.constants.KeyValueConstants

data class ReqReadDto (
    var filterKey : String? = null,
    var filterValue : String? = null,
    var search : String? = null,
    var mode : String? = null
)

enum class DataMode{
    COMPLETE,
    CONCISE
}