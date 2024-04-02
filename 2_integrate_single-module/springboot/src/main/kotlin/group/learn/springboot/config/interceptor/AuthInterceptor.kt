package group.learn.springboot.config.interceptor


import com.fasterxml.jackson.databind.ObjectMapper
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.utils.JwtGenerator
import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class AuthInterceptor(
    @Value("\${header.request.api-key}") private val apiKey : String
) : HandlerInterceptor{

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val apiKeyRequest = request.getHeader("X-TOKEN")


        if(apiKeyRequest == null){
            val body : BaseResponse<String> = BaseResponse(
                status = 403,
                message = "You dont have permission"
            )
            internalServerError(body,response)
            return false
        }

        try{
            JwtGenerator().decodeJwt(apiKeyRequest)["id"] ?: throw RuntimeException("Invalid Token")
        }catch (e : ExpiredJwtException){
            e.printStackTrace()
            val body : BaseResponse<String> = BaseResponse(401, "Invalid token", null)
            internalServerError(body,response)
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