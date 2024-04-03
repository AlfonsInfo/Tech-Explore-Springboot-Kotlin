package group.learn.springboot.service.impl

import group.learn.springboot.constants.SecurityConstants
import group.learn.springboot.domain.dto.request.ReqReadDto
import group.learn.springboot.domain.dto.request.ReqUpsertMusicDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.domain.dto.response.ResMusicDto
import group.learn.springboot.domain.entity.MusicEntity
import group.learn.springboot.domain.repository.GenreRepository
import group.learn.springboot.domain.repository.MusicRepository
import group.learn.springboot.domain.repository.UserTypeRepository
import group.learn.springboot.service.MusicService
import group.learn.springboot.utils.JwtGenerator
import jakarta.servlet.http.HttpServletRequest
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import java.util.*


@Service
class MusicServiceImpl(
    private val genreRepository: GenreRepository,
    private val typeRepository: UserTypeRepository,
    private val musicRepository: MusicRepository,
    private val jwtGenerator: JwtGenerator,
) : MusicService {

    @Transactional
    override fun create(request: ReqUpsertMusicDto): BaseResponse<String> {
        //* check type
        if(request.type == null){
            request.type = typeRepository.findByName("T002").get().id.toString()
        }

        val type = typeRepository.findById(UUID.fromString(request.type)).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND,"Type Not Found")
        }
        val joiner = StringJoiner(", " , "[","]")
        request.idGenre?.forEach {
            //* check exist or not
            genreRepository.findById(it.toInt())
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Genre $it Not found") }
            joiner.add(it)
        }
        val listGenre = joiner.toString()

        val musicEntity = MusicEntity()
        musicEntity.musicName = request.musicName
        musicEntity.type = type
        musicEntity.idGenre = listGenre
        musicEntity.dtAdded= LocalDateTime.now()
        musicEntity.dtUpdated = LocalDateTime.now()
        musicRepository.save(musicEntity)
        return BaseResponse(data = musicEntity.id.toString())
    }

    override fun read(request: ReqReadDto, httpServletRequest: HttpServletRequest): BaseResponse<List<ResMusicDto>> {
        //* check header
        val token = httpServletRequest.getHeader(SecurityConstants.PARAMS_TOKEN)
        val jwtClaim = jwtGenerator.decodeJwt(token)
        val type = jwtClaim["type"]
        val typeId = jwtClaim["typeId"]
        val dataForResp = mutableListOf<ResMusicDto>()

        if(Objects.equals(type, "T002")){
            val dataFromQuery = musicRepository.findBySearch(request.search!!)
            dataFromQuery.forEach{entity ->
                val data = ResMusicDto()
                data.id = entity.id.toString()
                data.musicName = entity.musicName!!
                data.type= entity.type?.name!!
                dataForResp.add(data)
            }
        }else{
//            val typeForShow = typeRepository.findById().get()
//            val dataFromQuery = musicRepository
//                .findByMusicNameOrIdGenreOrTypeContaining(
//                request.search.let { "%$it%" },
//                request.search.let { "%$it%" },
//                UUID.fromString(typeId as String)
//                )
            val dataFromQuery = musicRepository.findBySearchAndTypeId(request.search!!, UUID.fromString(typeId as String))


            dataFromQuery.forEach{entity ->
                val data = ResMusicDto()
                data.id = entity.id.toString()
                data.musicName = entity.musicName!!
                data.type= entity.type?.name!!
                dataForResp.add(data)
            }

        }
        return BaseResponse(data = dataForResp)
    }

    override fun readDetail(id: String, request: ReqReadDto): BaseResponse<ResMusicDto> {
        val dataFromQuery = musicRepository.findById(UUID.fromString(id))
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND,"music not found") }
            val data = ResMusicDto()
            data.id = dataFromQuery.id.toString()
            data.musicName = dataFromQuery.musicName!!
            data.type= dataFromQuery.type?.name!!
            return BaseResponse(data = data)
    }

    override fun update(id: String, request: ReqUpsertMusicDto): BaseResponse<String> {
        //* search and validate music
        val musicEntity = musicRepository.findById(UUID.fromString(id)).orElseThrow{
            ResponseStatusException(HttpStatus.NOT_FOUND, "Music Not FOund")
        }
        //* check type
        val type = typeRepository.findById(UUID.fromString(request.type)).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND,"Type Not Found")
        }
        val joiner = StringJoiner(", " , "[","]")
        request.idGenre?.forEach {
            //* check exist or not
            genreRepository.findById(it.toInt())
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Genre $it Not found") }
            joiner.add(it)
        }
        val listGenre = joiner.toString()

        musicEntity.musicName = request.musicName
        musicEntity.type = type
        musicEntity.idGenre = listGenre
        musicEntity.dtAdded= LocalDateTime.now()
        musicEntity.dtUpdated = LocalDateTime.now()
        musicRepository.save(musicEntity)
        return BaseResponse(data = musicEntity.id.toString())
    }

    override fun delete(id: String): BaseResponse<String> {
        genreRepository.deleteById(id.toInt())
        return BaseResponse()
    }
}