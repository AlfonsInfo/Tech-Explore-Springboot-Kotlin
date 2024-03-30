package group.learn.springboot.exception

import group.learn.springboot.domain.dto.response.ResMessageDto
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(DataNotFoundException::class)
    fun handleDataNotFound(exception : DataNotFoundException) : ResponseEntity<ResMessageDto<*>>{
        return ResponseEntity.badRequest().body(ResMessageDto<Any?>(
            status = 400,
            message = exception.message.toString()
        ))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleArgumentNotValidException(exception : MethodArgumentNotValidException) : ResponseEntity<Any>{
        val errors = mutableListOf<String>()
        exception.bindingResult.fieldErrors.forEach{
            errors.add(it.defaultMessage!!)
        }
        val result = mapOf<String, Any>("status" to "F","error" to "field", "message" to errors)
        return ResponseEntity.badRequest().body(result)
    }


    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleArgumentNotValidException(exception : DataIntegrityViolationException) : ResponseEntity<Any>{
        var field = extractColumn(exception.cause?.message.toString())
        val errorMessage = if (field.isNullOrBlank()) {
            exception.cause?.message.toString()
        } else {
            "Value for field ${extractColumn(exception.cause?.message.toString())} is duplicate"
        }
        val result = mapOf<String, Any>("status" to "F","error" to "field", "message" to errorMessage)
        return ResponseEntity.badRequest().body(result)
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
        return null // Return null if column extraction fails
    }
}