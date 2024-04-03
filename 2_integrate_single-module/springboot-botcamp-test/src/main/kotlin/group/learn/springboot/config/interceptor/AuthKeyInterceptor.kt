package group.learn.springboot.config.interceptor

import com.fasterxml.jackson.databind.ObjectMapper
import group.learn.springboot.constants.SecurityConstants
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.exception.CustomUnauthorizedException
import group.learn.springboot.utils.JwtGenerator
import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor


@Component
class AuthKeyInterceptor(
    private val jwtGenerator: JwtGenerator,
    private val objectMapper: ObjectMapper,
) : HandlerInterceptor{
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        try{
            val jwtToken = request.getHeader(SecurityConstants.PARAMS_TOKEN)
            jwtGenerator.decodeJwt(jwtToken)
            //apakah perlu pengecekan id dari hasil decode sesuai dengan database
            return true
        }catch(e : Exception){
           throw CustomUnauthorizedException("Invalid Token")
        }
    }
}