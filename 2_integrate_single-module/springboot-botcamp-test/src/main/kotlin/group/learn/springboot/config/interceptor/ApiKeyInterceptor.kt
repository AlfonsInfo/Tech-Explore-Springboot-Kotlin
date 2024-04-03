package group.learn.springboot.config.interceptor

import com.fasterxml.jackson.databind.ObjectMapper
import group.learn.springboot.constants.SecurityConstants
import group.learn.springboot.exception.CustomUnauthorizedException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.util.*

@Component
class ApiKeyInterceptor(
    @Value("\${header.request.api-key}") private val apiKey : String,
    private val objectMapper: ObjectMapper
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val requestApiKey = request.getHeader(SecurityConstants.PARAMS_API_KEY)
        if(requestApiKey != null &&  Objects.equals(apiKey, requestApiKey)){
            return true;
        }
        throw CustomUnauthorizedException(SecurityConstants.MESSAGE_INVALID_API_KEY)
        //internalServerError()
    }
}