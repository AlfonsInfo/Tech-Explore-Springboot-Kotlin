package group.learn.springboot.domain.dto.response

import com.fasterxml.jackson.annotation.JsonInclude

data class BaseResponse<T>(
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val status : Int? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val message : String? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val data : T? =null
)