package group.learn.springboot.controller

import feign.Response
import group.learn.springboot.constants.SecurityConstants
import group.learn.springboot.domain.dto.request.ReqInsertUserDto
import group.learn.springboot.domain.dto.request.ReqUpdateUserDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.domain.dto.response.ResUserDto
import group.learn.springboot.service.UserService
import group.learn.springboot.service.impl.UserServiceImpl
import group.learn.springboot.utils.JwtGenerator
import io.swagger.v3.oas.annotations.Parameter
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Objects

@RestController
@RequestMapping("/api/v1/user")
class UserController (
    val userService: UserService,
    val jwtGenerator: JwtGenerator
) {

    @PostMapping("register")
    fun insert(@RequestBody request : ReqInsertUserDto) : ResponseEntity<BaseResponse<String>>{
        val response = userService.insert(request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("list")
    fun getAll() : ResponseEntity<BaseResponse<List<ResUserDto>>>{
        val response = userService.getAll()
        return ResponseEntity.ok(response)
    }

    @GetMapping( value = ["/detail/{id}", "/detail"])
    fun getDetail(@Parameter(required=false) @PathVariable(name = "id", required = false) id : String?,
                  request : HttpServletRequest) : ResponseEntity<BaseResponse<ResUserDto>>{

        val idRequest = id ?:  jwtGenerator.decodeJwt(request.getHeader(SecurityConstants.PARAMS_TOKEN))["id"].toString()
        val response = userService.find(idRequest)
        return ResponseEntity.ok(response)
    }

    @PutMapping("/update/{id}")
    fun update(
        @PathVariable(name = "id") id : String,
        @RequestBody request: ReqUpdateUserDto
    ) : ResponseEntity<BaseResponse<String>>{
        val response = userService.update(id, request)
        return ResponseEntity.ok(response)
    }



}