package group.learn.multidatasource.exception

import group.learn.multidatasource.domain.dto.response.ResMessageDto
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
@Primary
class GlobalExceptionHandler : ResponseEntityExceptionHandler()
{
    @ExceptionHandler(DataNotFound::class)
    fun handleDataNotFoundException(exception: RuntimeException): ResponseEntity<ResMessageDto<Any>> {
        val message: ResMessageDto<Any> = ResMessageDto(
            404, exception.message, null
        )
        return ResponseEntity<ResMessageDto<Any>>(message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(DuplicateDataException::class)
    fun handleDuplicateDataException(exception: RuntimeException): ResponseEntity<ResMessageDto<Any>> {
        val message: ResMessageDto<Any> = ResMessageDto(
            400, exception.message, null
        )
        return ResponseEntity<ResMessageDto<Any>>(message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequest::class)
    fun handleBadRequestException(exception: RuntimeException): ResponseEntity<ResMessageDto<Any>> {
        val message: ResMessageDto<Any> = ResMessageDto(
            400, exception.message, null
        )
        return ResponseEntity<ResMessageDto<Any>>(message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorized(exception: RuntimeException): ResponseEntity<ResMessageDto<Any>> {
        val message: ResMessageDto<Any> = ResMessageDto(
            401, exception.message, null
        )
        return ResponseEntity<ResMessageDto<Any>>(message, HttpStatus.UNAUTHORIZED)
    }
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        // if need multiple error Messages
        // val errorMessages = ex.allErrors.map { it.defaultMessage }
        val errorMessage = ex.allErrors[0].defaultMessage
        return ResponseEntity.badRequest().body(
            ResMessageDto(
            400,
            errorMessage,
            null
        )
        )

    }
}