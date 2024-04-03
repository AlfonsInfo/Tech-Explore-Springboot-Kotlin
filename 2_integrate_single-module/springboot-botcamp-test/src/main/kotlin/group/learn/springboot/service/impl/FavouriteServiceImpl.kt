package group.learn.springboot.service.impl

import group.learn.springboot.constants.SecurityConstants
import group.learn.springboot.domain.dto.request.ReqReadDto
import group.learn.springboot.domain.dto.request.ReqUpsertFavDto
import group.learn.springboot.domain.dto.request.ReqUpsertMusicDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.domain.dto.response.ResFavDto
import group.learn.springboot.domain.dto.response.ResFavSingleMusicDto
import group.learn.springboot.domain.dto.response.ResMusicDto
import group.learn.springboot.domain.entity.FavouriteEntity
import group.learn.springboot.domain.entity.MusicEntity
import group.learn.springboot.domain.repository.FavouriteRepository
import group.learn.springboot.domain.repository.GenreRepository
import group.learn.springboot.domain.repository.MusicRepository
import group.learn.springboot.domain.repository.UserTypeRepository
import group.learn.springboot.service.FavouriteService
import group.learn.springboot.service.MusicService
import group.learn.springboot.utils.JwtGenerator
import jakarta.servlet.http.HttpServletRequest
import jakarta.transaction.Transactional
import org.hibernate.sql.results.graph.internal.ImmutableFetchList
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import java.util.*


@Service
class FavouriteServiceImpl(
    private val favouriteRepository: FavouriteRepository,
    private val musicRepository: MusicRepository,
    private val jwtGenerator: JwtGenerator,
) : FavouriteService {
    override fun create(request: ReqUpsertFavDto, httpServletRequest : HttpServletRequest): BaseResponse<String> {
        val token = httpServletRequest.getHeader(SecurityConstants.PARAMS_TOKEN)
        val user = jwtGenerator.decodeJwt(token)["username"]
        val favEntity = FavouriteEntity()

        favEntity.idMusic = request.idMusic
        favEntity.userAdded = user as String
        favEntity.dtAdded = LocalDateTime.now()
        favEntity.dtUpdated = LocalDateTime.now()
        favouriteRepository.save(favEntity)
        return BaseResponse()
    }

    override fun readByUser(httpServletRequest: HttpServletRequest): BaseResponse<List<ResFavSingleMusicDto>> {
        val token = httpServletRequest.getHeader(SecurityConstants.PARAMS_TOKEN)
        val user = jwtGenerator.decodeJwt(token)["username"]
        val data = favouriteRepository.findAllByUserAdded(user as String)
        val dataForResp = mutableListOf<ResFavSingleMusicDto>()
        data.forEach {
            val findMusic = musicRepository.findById(UUID.fromString(it.idMusic))
                .orElseThrow {
                    ResponseStatusException(HttpStatus.NOT_FOUND,"musik tidak ditemukan")
                }
            val resFav = ResFavSingleMusicDto()
            resFav.music = findMusic.musicName
            dataForResp.add(resFav)
        }
        return BaseResponse(data = dataForResp)
    }

    override fun read(request: ReqReadDto, httpServletRequest: HttpServletRequest): BaseResponse<List<ResFavDto>> {
        val data = favouriteRepository.findAll()
        val dataForResp = mutableListOf<ResFavDto>()
        data.forEach {
            val res = ResFavDto()
            res.idMusic = it.idMusic
            res.userAdded = it.userAdded
            dataForResp.add(res)
        }
        return BaseResponse(data =  dataForResp)

    }

    override fun readDetail(id: String, request: ReqReadDto): BaseResponse<ResFavDto> {
        val data = favouriteRepository.findById(UUID.fromString(id)).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found")
        }

        val res = ResFavDto()
        res.idMusic = data.idMusic
        res.userAdded = data.userAdded

        return BaseResponse(data =  res)
        }




}