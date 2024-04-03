package group.learn.springboot.exception

import group.learn.springboot.domain.dto.response.BaseResponse
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(CustomUnauthorizedException::class)
    fun handleUnAuthorizedUser(exception: CustomUnauthorizedException)
    : ResponseEntity<BaseResponse<*>>{
        val message =  exception.message
        val errorStatus = HttpStatus.FORBIDDEN.value()
        val bodyResp = BaseResponse<String>(status = errorStatus, message = message!!)
        return ResponseEntity
            .status(errorStatus)
            .body(bodyResp)
    }

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(exception: ResponseStatusException)
            : ResponseEntity<BaseResponse<*>>
    {
        val message =  exception.reason
        val errorStatus = HttpStatus.BAD_REQUEST.value()
        val bodyResp = BaseResponse<String>(status = errorStatus, message = message!!)
        return ResponseEntity
            .status(errorStatus)
            .body(bodyResp)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleArgumentNotValidException(exception : DataIntegrityViolationException) : ResponseEntity<BaseResponse<*>>{
        var field = extractColumn(exception.cause?.message.toString())
        val errorMessage = if (field.isNullOrBlank()) {
            exception.cause?.message.toString()
        } else {
            "Value for field ${extractColumn(exception.cause?.message.toString())} is duplicate"
        }

        val bodyResp = BaseResponse<String>(status = HttpStatus.BAD_REQUEST.value(), message = errorMessage)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyResp)
    }

    @ExceptionHandler(Exception::class)
    fun fallbackException(exception : Exception) : ResponseEntity<BaseResponse<*>>{
        val message =  "Terjadi Kesalahan Pada Sistem : ${exception.message}"
        val errorStatus = HttpStatus.INTERNAL_SERVER_ERROR.value()
        val bodyResp = BaseResponse<String>(status = errorStatus, message = message!!)
        return ResponseEntity
            .status(errorStatus)
            .body(bodyResp)
    }


    fun extractColumn(errorMessage: String): String? {
        // Find the index of the opening parenthesis after "Key ("
        var startIndex = errorMessage.indexOf("Key (")
        if (startIndex != -1) {
            // Adjust the start index to the character after "Key ("
            startIndex += 5
            // Find the index of the closing parenthesis after the start index
            val endIndex = errorMessage.indexOf(")", startIndex)
            if (endIndex != -1) {
                // Extract the substring between the start and end indexes
                val keyInfo = errorMessage.substring(startIndex, endIndex)
                // Split the key info by "=" to get the column name
                val parts = keyInfo.split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                if (parts.size > 0) {
                    // Trim the column name and return
                    return parts[0].trim { it <= ' ' }
                }
            }
        }
        return null // R
}
}