package group.learn.springboot.controller

import group.learn.springboot.constants.KeyValueConstants
import group.learn.springboot.domain.dto.request.*
import group.learn.springboot.domain.dto.response.*
import group.learn.springboot.domain.repository.FavouriteRepository
import group.learn.springboot.service.FavouriteService
import group.learn.springboot.service.GenreService
import group.learn.springboot.service.MusicService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/favourite")
class FavouriteController(
    private val favouriteService: FavouriteService
) {

    @PostMapping
    fun create(
        @RequestBody request : ReqUpsertFavDto,
        httpServletRequest: HttpServletRequest
    ) : ResponseEntity<BaseResponse<String>> {
        val response = favouriteService.create(request,httpServletRequest)
        return ResponseEntity.ok(response)
    }

    @GetMapping
    fun read(
        @RequestParam(required = false) search : String?,
        httpServletRequest: HttpServletRequest
    ) : ResponseEntity<BaseResponse<List<ResFavDto>>> {
        val request = ReqReadDto()
        val response = favouriteService.read(request,httpServletRequest)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/current")
    fun readByUser(
        httpServletRequest: HttpServletRequest
    ) : ResponseEntity<BaseResponse<List<ResFavSingleMusicDto>>> {
        val response = favouriteService.readByUser(httpServletRequest)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun readDetail(
        @PathVariable(name = "id") id : String,
        @RequestParam(required = false) mode : String?
    ) : ResponseEntity<BaseResponse<ResFavDto>> {
        val request = ReqReadDto(
            mode = mode ?: KeyValueConstants.MODE_VALUE_CONCISE
        )

        val response = favouriteService.readDetail(id, request)
        return ResponseEntity.ok(response)
    }
}