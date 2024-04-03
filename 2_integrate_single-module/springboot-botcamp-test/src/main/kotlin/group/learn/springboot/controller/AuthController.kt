package group.learn.springboot.controller

import group.learn.springboot.domain.dto.request.ReqEncodeJwtDto
import group.learn.springboot.domain.dto.request.ReqLoginDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.domain.dto.response.ResLoginDto
import group.learn.springboot.domain.repository.UserRepository
import group.learn.springboot.utils.JwtGenerator
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val userRepository: UserRepository,
    private val jwtGenerator: JwtGenerator,
    private val passwordEncoder: PasswordEncoder
) {


    @PostMapping("/login")
    fun login(@RequestBody request : ReqLoginDto) : ResponseEntity<BaseResponse<ResLoginDto>>{

        val data = userRepository.findByUsername(request.username!!)
            .orElseThrow { ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credential Not Match")
        }

        val checkpassword = passwordEncoder.matches(request.password!!, data.password )
        if(!checkpassword){
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credential Not Match")
        }


        val jwt = ReqEncodeJwtDto()
        jwt.id = data.id.toString()
        jwt.username = data.username
        jwt.email = data.email
        jwt.type= data.type?.name
        jwt.typeId = data.type?.id.toString()

        val jwtToken = jwtGenerator.createJWT(jwt)
        val response = ResLoginDto()
        response.id = data.id.toString()
        response.token =  jwtToken
        return ResponseEntity.ok(BaseResponse(data = response))
    }

}