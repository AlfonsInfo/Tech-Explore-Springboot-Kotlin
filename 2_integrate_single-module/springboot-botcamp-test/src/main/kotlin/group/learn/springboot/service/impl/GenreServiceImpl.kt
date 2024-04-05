package group.learn.springboot.service.impl

import group.learn.springboot.constants.KeyValueConstants
import group.learn.springboot.domain.dto.request.ReqReadDto
import group.learn.springboot.domain.dto.request.ReqUpsertGenreDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.domain.dto.response.ResMusicByGenreDto
import group.learn.springboot.domain.dto.response.ResReadGenreDto
import group.learn.springboot.domain.dto.response.ResReadUserTypeDto
import group.learn.springboot.domain.entity.GenreEntity
import group.learn.springboot.domain.repository.GenreRepository
import group.learn.springboot.domain.repository.MusicRepository
import group.learn.springboot.service.GenreService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*


@Service
class GenreServiceImpl(
    private val genreRepository: GenreRepository,
    private val musicRepository: MusicRepository,
) : GenreService {
    override fun create(request: ReqUpsertGenreDto): BaseResponse<String> {
        val genreEntity = GenreEntity()
        genreEntity.name = request.name
        genreEntity.desc = request.desc
        genreRepository.save(genreEntity)
        return BaseResponse(data = genreEntity.id.toString())
    }

    override fun read(request: ReqReadDto): BaseResponse<List<ResReadGenreDto>> {
        val dataForResp = mutableListOf<ResReadGenreDto>()

        if (Objects.equals(request.mode, KeyValueConstants.MODE_VALUE_COMPLETE)) {
            val dataFromGenre = genreRepository.findAll()
            dataFromGenre.forEach { genreEntity ->
                val dataFromMusic = musicRepository.findAllByIdGenreContains(genreEntity.id!!.toInt())

               //* 1 dataFromGenre - 1 res
                val resReadGenreDto = ResReadGenreDto(
                    id = genreEntity.id.toString(),
                    name = genreEntity.name,
                    desc = genreEntity.desc,
                    music = dataFromMusic.map { musicEntity ->
                        ResMusicByGenreDto(
                            musicName = musicEntity.musicName,
                            type = musicEntity.type?.name,
                        )
                    }
                )
                // Menambahkan objek ResReadGenreDto ke dalam dataForResp
                dataForResp.add(resReadGenreDto)
            }

        }else{
            val dataFromGenre = genreRepository.findAll()

            //mapping data
            dataFromGenre.forEach{entity ->
                val data = ResReadGenreDto()
                data.id = entity.id.toString()
                data.name = entity.name!!
                data.desc= entity.desc!!
                dataForResp.add(data)
            }
        }
        return BaseResponse(data = dataForResp)
    }

    override fun readDetail(id: String, request: ReqReadDto): BaseResponse<ResReadGenreDto> {
        val dataFromGenre = genreRepository.findById(id.toInt()).orElseThrow {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Genre Not Found")
        }

        if (Objects.equals(request.mode, KeyValueConstants.MODE_VALUE_COMPLETE)) {
                val dataFromMusic = musicRepository.findAllByIdGenreContains(dataFromGenre.id!!.toInt())

                val resReadGenreDto = ResReadGenreDto(
                    id = dataFromGenre.id.toString(),
                    name = dataFromGenre.name,
                    desc = dataFromGenre.desc,
                    music = dataFromMusic.map { musicEntity ->
                        ResMusicByGenreDto(
                            musicName = musicEntity.musicName,
                            type = musicEntity.type?.name,
                        )
                    }
                )
            return BaseResponse(data = resReadGenreDto)
        }else{
            val data = ResReadGenreDto()
            data.id = dataFromGenre.id.toString()
            data.name = dataFromGenre.name!!
            data.desc= dataFromGenre.desc!!
            return BaseResponse(data = data)
        }
    }

    override fun update(id: String, request: ReqUpsertGenreDto): BaseResponse<String> {
        //* find data for update
        val genreEntity = genreRepository
            .findById(id.toInt())
            .orElseThrow{
                ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Genre Not Found"
                )
            }
        // update data
        genreEntity.name = request.name
        genreEntity.desc = request.desc
        genreRepository.save(genreEntity)
        return BaseResponse(data = genreEntity.id.toString())
    }

    override fun delete(id: String): BaseResponse<String> {
        //find data
        val genreEntity = genreRepository
            .findById(id.toInt())
            .orElseThrow{
                ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Genre Not Found"
                )
            }
        if(musicRepository.existsByGenreIdContains(genreEntity.id!!.toString())){
            throw  ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Can not delete genre, there are music in this genre"
            )
        }

        genreRepository.deleteById(id.toInt())
        return BaseResponse()
    }
}