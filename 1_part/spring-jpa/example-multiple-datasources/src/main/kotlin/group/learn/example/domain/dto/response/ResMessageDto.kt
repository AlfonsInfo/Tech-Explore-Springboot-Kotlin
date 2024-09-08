package group.learn.example.domain.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import group.learn.example.domain.common.Pagination
import group.learn.example.domain.constant.ConstantVariable

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResMessageDto<T>(
    val status: Int? = 200,
    val message: String? = ConstantVariable.SUCCESS_MESSAGE,
    val data: T? = null,
    val isAvailable: Boolean? = null,
    val pagination: Pagination? = null,


    //file Excel
    val totalUploaded: Int? = null,
    val totalSubmit: Int? = null,
    val successCount: Int? = null,
    val failureCount: Int? = null,
    val dataUpload: T? = null,
    val dataFailed: T? = null,
)
