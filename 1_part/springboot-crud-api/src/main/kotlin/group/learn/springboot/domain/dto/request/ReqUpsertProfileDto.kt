package group.learn.springboot.domain.dto.request

import jakarta.persistence.Column
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.UniqueElements

data class ReqUpsertProfileDto (
    @field:NotBlank(message = "name not null request cannot be blank")
    @field:NotEmpty (message = "name not null request cannot be empty")
    @field:NotNull (message = "name not null request cannot be null")
    @field:Size(max = 100 , message = "Max 100 character")
    @field:Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    var name : String ? = null,

    @field:NotBlank(message = "username not null request cannot be blank")
    @field:NotEmpty (message = "username not null request cannot be empty")
    @field:NotNull (message = "username not null request cannot be null")
    @field:Size(max = 32 , message = "Max 32 character")
    // unique validation
    var username : String ? = null,

    @field:NotBlank(message = "email not null request cannot be blank")
    @field:NotEmpty (message = "email not null request cannot be empty")
    @field:Email (message = "Not Right Email Format")
    //* unique validation
    var email : String ? = null,

    @field:NotBlank(message = "password not null request cannot be blank")
    @field:NotEmpty (message = "password not null request cannot be empty")
    @field:NotNull (message = "password not null request cannot be null")
    @field:Size(max = 32 , message = "Max 32 character")
    var password : String ? = null,

    var avatar : String? = null
)