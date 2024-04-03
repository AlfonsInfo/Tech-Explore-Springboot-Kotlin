package group.learn.springboot.controller

import group.learn.springboot.constants.KeyValueConstants
import group.learn.springboot.domain.dto.request.ReqReadDto
import group.learn.springboot.domain.dto.request.ReqUpsertGenreDto
import group.learn.springboot.domain.dto.request.ReqUpsertMusicDto
import group.learn.springboot.domain.dto.request.ReqUpsertUserTypeDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.domain.dto.response.ResMusicDto
import group.learn.springboot.domain.dto.response.ResReadUserTypeDto
import group.learn.springboot.service.GenreService
import group.learn.springboot.service.MusicService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/music")
class MusicController(
    private val musicService: MusicService
) {

    @PostMapping
    fun create(
        @RequestBody request : ReqUpsertMusicDto
    ) : ResponseEntity<BaseResponse<String>> {
        val response = musicService.create(request)
        return ResponseEntity.ok(response)
    }

    @GetMapping
    fun read(
        @RequestParam(required = false) mode : String?,
        @RequestParam(required = false) search : String?,
        httpServletRequest: HttpServletRequest
    ) : ResponseEntity<BaseResponse<List<ResMusicDto>>> {
        //prepare request
        val request = ReqReadDto(
            mode = mode ?: KeyValueConstants.MODE_VALUE_CONCISE,
            search = search ?: ""
        )

        val response = musicService.read(request,httpServletRequest)
        return ResponseEntity.ok(response)
    }
//
//    @GetMapping("/{id}")
//    fun readDetail(
//        @PathVariable(name = "id") id : String,
//        @RequestParam(required = false) mode : String?
//    ) : ResponseEntity<BaseResponse<ResReadUserTypeDto>> {
//        val request = ReqReadDto(
//            mode = mode ?: KeyValueConstants.MODE_VALUE_CONCISE
//        )
//
//        val response = userTypeService.readDetail(id, request)
//        return ResponseEntity.ok(response)
//    }



    @PutMapping("/{id}")
    fun update(
        @PathVariable(name = "id") id : String,
        @RequestBody request : ReqUpsertMusicDto
    ) : ResponseEntity<BaseResponse<String>> {
        val response = musicService.update(id, request)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable(name = "id") id : String,
    ) : ResponseEntity<BaseResponse<String>> {
        val response = musicService.delete(id)
        return ResponseEntity.ok(response)
    }
}