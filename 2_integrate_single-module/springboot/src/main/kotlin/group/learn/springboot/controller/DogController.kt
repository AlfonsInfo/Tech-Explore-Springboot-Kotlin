package group.learn.springboot.controller

import group.learn.springboot.domain.dto.response.ResDogListDto
import group.learn.springboot.domain.dto.response.ResRandomImageDto
import group.learn.springboot.service.DogService
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.io.IOException


@RestController
@RequestMapping( "/api/v1/dog")
class DogController(
    val dogService: DogService
) {

    @GetMapping("/link")
    fun detail() : ResponseEntity<ResRandomImageDto>{
        return ResponseEntity.ok(dogService.getDogImage())
    }

    @GetMapping("/list")
    fun list() : ResponseEntity<ResDogListDto>{
        return ResponseEntity.ok(dogService.getDogImageRestClient())
    }

//    @GetMapping("/download")
//    @ResponseBody
//    @Throws(IOException::class)
//    fun getImage (): ResponseEntity<Any> {
//        val downloadUtil = FileDownloadUtil()
//
//        val resource: Resource? = try {
//            downloadUtil.getFileAsResource(dogService.getDogImage().message)
//        } catch (e: IOException) {
//            return ResponseEntity.internalServerError().build()
//        }
//
//        if (resource == null) {
//            return ResponseEntity("File not found", HttpStatus.NOT_FOUND)
//        }
//
//        val contentType = "application/octet-stream"
//        val headerValue = "attachment; filename=\"${resource.filename}\""
//
//        return ResponseEntity.ok()
//            .contentType(MediaType.parseMediaType(contentType))
//            .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
//            .body(resource)
//    }
}