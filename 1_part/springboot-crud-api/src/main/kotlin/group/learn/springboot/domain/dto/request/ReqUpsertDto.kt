package group.learn.springboot.domain.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class ReqUpsertDto(
    @field:NotBlank (message = "name not null request cannot be blank")
    @field:NotEmpty (message = "name not null request cannot be empty")
    val name : String,

    @field:NotBlank (message = "merk not null request cannot be empty")
    @field:NotEmpty (message = "merk not null request cannot be empty")
    val merk : String)
