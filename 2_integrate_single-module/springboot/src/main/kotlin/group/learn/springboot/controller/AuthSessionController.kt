package group.learn.springboot.controller


import group.learn.springboot.domain.dto.request.ReqLoginDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.domain.dto.response.ResDecodeJwtDtoProfile
import group.learn.springboot.domain.dto.response.ResLoginDto
import group.learn.springboot.service.AuthSessionService
import group.learn.springboot.utils.JwtGenerator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthSessionController (
    val authSessionService: AuthSessionService
){

    @PostMapping("/login")
    fun login(@RequestBody request : ReqLoginDto) : ResponseEntity<BaseResponse<ResLoginDto>> {
        val response = authSessionService.login(request)

        if(response != null){
            return ResponseEntity.ok(BaseResponse(data = response))
        }else{
            val baseResponse = BaseResponse<ResLoginDto>(message = "Username and password wrong", status = 401)
            return ResponseEntity(baseResponse, HttpStatus.UNAUTHORIZED)
        }
    }
    @PostMapping("/validate-token")
    fun validateToken(@RequestHeader(name = "X-TOKEN") token : String) : ResponseEntity<BaseResponse<ResDecodeJwtDtoProfile>>{
        try{

        val claims = JwtGenerator().decodeJwt(token)
        return ResponseEntity.ok(
            BaseResponse(
                message = "Token Valid",
                data = ResDecodeJwtDtoProfile(
                    claims["id"].toString(),
                    claims["name"].toString(),
                    claims["username"].toString(),
                    claims["email"].toString(),
                )
            )
        )
        }catch(e : Exception){
            return ResponseEntity(BaseResponse(), HttpStatus.UNAUTHORIZED)
        }
    }

}