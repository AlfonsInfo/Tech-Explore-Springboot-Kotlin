package group.learn.springboot.controller

import group.learn.springboot.constants.KeyValueConstants
import group.learn.springboot.domain.dto.request.ReqReadDto
import group.learn.springboot.domain.dto.request.ReqUpsertGenreDto
import group.learn.springboot.domain.dto.request.ReqUpsertUserTypeDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.domain.dto.response.ResReadGenreDto
import group.learn.springboot.domain.dto.response.ResReadUserTypeDto
import group.learn.springboot.service.GenreService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/genre")
class GenreController(
    private val genreService: GenreService
) {

    @PostMapping
    fun create(
        @RequestBody request : ReqUpsertGenreDto
    ) : ResponseEntity<BaseResponse<String>> {
        val response = genreService.create(request)
        return ResponseEntity.ok(response)
    }


    @GetMapping
    fun read(
        @RequestParam(required = false) mode : String?
    ) : ResponseEntity<BaseResponse<List<ResReadGenreDto>>> {
        //prepare request
        val request = ReqReadDto(
            mode = mode ?: KeyValueConstants.MODE_VALUE_CONCISE
        )

        //hit service
        val response = genreService.read(request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun readDetail(
        @PathVariable(name = "id") id : String,
        @RequestParam(required = false) mode : String?
    ) : ResponseEntity<BaseResponse<ResReadGenreDto>> {
        val request = ReqReadDto(
            mode = mode ?: KeyValueConstants.MODE_VALUE_CONCISE
        )

        val response = genreService.readDetail(id, request)
        return ResponseEntity.ok(response)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable(name = "id") id : String,
        @RequestBody request : ReqUpsertGenreDto
    ) : ResponseEntity<BaseResponse<String>> {
        val response = genreService.update(id, request)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable(name = "id") id : String,
    ) : ResponseEntity<BaseResponse<String>> {
        val response = genreService.delete(id)
        return ResponseEntity.ok(response)
    }
}