package group.learn.springboot.config.interceptor



import com.fasterxml.jackson.databind.ObjectMapper
import group.learn.springboot.domain.dto.response.BaseResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class RequestInterceptor(
    @Value("\${header.request.api-key}") private val apiKey : String
) : HandlerInterceptor{

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
       val apiKeyRequest = request.getHeader("API-KEY")
        if(apiKeyRequest != apiKey){
            val body = BaseResponse<String>(status = 400, message = "API KEY Failed")
            internalServerError(body, response)
            return false
        }
        return super.preHandle(request, response, handler)
    }

    fun internalServerError(body : BaseResponse<String>, response : HttpServletResponse) : HttpServletResponse{
        response.status = HttpStatus.FORBIDDEN.value()
        response.contentType = "application/json"
        response.writer.write(convertObjectToJson(body))

        return response
    }

    fun convertObjectToJson(dto : BaseResponse<String>) : String{
        return ObjectMapper().writeValueAsString(dto)
    }
}