package group.learn.springboot.domain.dto.response

import com.fasterxml.jackson.annotation.JsonInclude

data class BaseResponse<T> (
    val status : Int = 200,
    val message : String = "Success",

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val data : T? =null
)