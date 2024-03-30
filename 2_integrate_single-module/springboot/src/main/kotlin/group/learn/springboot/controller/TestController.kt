package group.learn.springboot.controller

import group.learn.springboot.domain.dto.request.ReqNumberDto
import group.learn.springboot.domain.dto.response.ResPersonBaseDto
import group.learn.springboot.domain.dto.response.ResPersonWithAgeDto
import group.learn.springboot.domain.dto.response.ResPersonWithRoleDto
import group.learn.springboot.service.BiodataService
import group.learn.springboot.service.impl.LogicService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api")
class TestController (
    private val logicService: LogicService,
    private val biodataService: BiodataService
){



    @GetMapping("/test")
    fun testGetMapping() : ResponseEntity<ResPersonBaseDto>{
        return  ResponseEntity.ok().body(biodataService.getBaseBioData())
    }

    @GetMapping("/user")
    fun getName(@RequestParam("age") age : String ) : ResponseEntity<ResPersonWithAgeDto>{
        return  ResponseEntity.ok().body(biodataService.getBioWithAge(age))
    }
    @GetMapping("/user/{role}")
    fun getUser(@PathVariable("role") role : String ) : ResponseEntity<ResPersonWithRoleDto>{
        return  ResponseEntity.ok().body(biodataService.getBioWithRole(role))
    }
    @PostMapping("/oddsOrEvent")
    fun getOddOrEvent(@RequestBody request : ReqNumberDto)
    : ResponseEntity<Any>{
        val response : LinkedHashMap<String,String> = LinkedHashMap()
        response["result"] = logicService.oddsOrEvent(request.number)
        return  ResponseEntity.ok().body(response)
    }



}