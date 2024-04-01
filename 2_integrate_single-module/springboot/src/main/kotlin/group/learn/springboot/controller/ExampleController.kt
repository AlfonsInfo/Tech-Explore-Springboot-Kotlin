package group.learn.springboot.controller

import group.learn.springboot.domain.dto.request.ReqDecodeJwtDto
import group.learn.springboot.domain.dto.request.ReqEncodeJwtDto
import group.learn.springboot.domain.dto.response.ResDecodeJWTDto
import group.learn.springboot.domain.dto.response.ResEncodeJWTDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.utils.JwtGenerator
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("jwt")
class ExampleController {

    @PostMapping("encode")
    fun encodeJwt(@RequestBody request : ReqEncodeJwtDto) : ResponseEntity<BaseResponse<ResEncodeJWTDto>>{
        val token = JwtGenerator().createJWT(request)
        return ResponseEntity.ok(BaseResponse(
                message = "Success Get Token JWT",
                data = ResEncodeJWTDto(request.id, token)
        ))
    }


    @PostMapping("decode")
    fun decodeJwt(@RequestBody request : ReqDecodeJwtDto) : ResponseEntity<BaseResponse<ResDecodeJWTDto>>{
      val claims = JwtGenerator().decodeJwt(request.token)
    return ResponseEntity.ok(
        BaseResponse(
            message = "Success Decode Jwt",
            data = ResDecodeJWTDto(
                claims["id"].toString(),
                claims["email"].toString(),
                claims["password"].toString(),
                claims["role"].toString(),
            )
        )
    )

    }

}