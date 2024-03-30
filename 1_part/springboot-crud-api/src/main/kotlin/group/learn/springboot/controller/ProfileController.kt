package group.learn.springboot.controller

import group.learn.springboot.domain.dto.request.ReqUpsertProfileDto
import group.learn.springboot.domain.dto.response.ResGetProfileDto
import group.learn.springboot.domain.dto.response.ResMessageDto
import group.learn.springboot.service.ProfileService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping( "/api/v1/profile")
class ProfileController (
    val profileService: ProfileService
){
    @PostMapping
    fun create(@Valid  @RequestBody request : ReqUpsertProfileDto) : ResponseEntity<ResMessageDto<String>>{
        val response = profileService.insert(request)
        return ResponseEntity.ok(response)
    }

    @PutMapping
    fun update(
        @RequestParam uuid  : UUID,
        @Valid @RequestBody request : ReqUpsertProfileDto) : ResponseEntity<ResMessageDto<String>>{
        val response = profileService.update(uuid,request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/detail")
    fun detail(@RequestParam uuid  : UUID) : ResponseEntity<ResMessageDto<ResGetProfileDto>>{
        val response = profileService.detail(uuid)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/list")
    fun list() : ResponseEntity<ResMessageDto<List<ResGetProfileDto>>>{
        val response = profileService.list()
        return ResponseEntity.ok(response)
    }

    @DeleteMapping
    fun delete(@RequestParam uuid : UUID) : ResponseEntity<ResMessageDto<String>>{
        val response = profileService.delete(uuid)
        return ResponseEntity.ok(response)
    }
}