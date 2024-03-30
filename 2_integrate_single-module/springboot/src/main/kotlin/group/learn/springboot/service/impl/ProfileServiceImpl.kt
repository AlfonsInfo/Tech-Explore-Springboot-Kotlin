package group.learn.springboot.service.impl

import group.learn.springboot.domain.dto.request.ReqUpsertProfileDto
import group.learn.springboot.domain.dto.response.ResGetProfileDto
import group.learn.springboot.domain.dto.response.ResMessageDto
import group.learn.springboot.domain.entity.ProfileEntity
import group.learn.springboot.domain.repository.ProfileRepository
import group.learn.springboot.exception.DataNotFoundException
import group.learn.springboot.rest.SvgProfileApiClient
import group.learn.springboot.service.ProfileService
import org.springframework.stereotype.Service
import java.util.*

private const val DATA_NOT_FOUND = "Profile Tidak Terdaftar"

@Service
class ProfileServiceImpl (
    val profileRepository: ProfileRepository,
    val svgProfileApiClient: SvgProfileApiClient
) : ProfileService{

    override fun insert(request: ReqUpsertProfileDto): ResMessageDto<String> {

        val avatar = if (request.avatar != null)
            getAvatar(request.avatar!!)
        else
            null

        val data = ProfileEntity(
            name =  request.name,
            email = request.email,
            username = request.username,
            password = request.password,
            avatar = avatar
        )
        profileRepository.save(data)
        return  ResMessageDto()
    }

    override fun update(id: UUID, request: ReqUpsertProfileDto): ResMessageDto<String> {
        //* find and validation
        val data  = profileRepository.findById(id)
        if(profileRepository.existsById(id)){
            throw DataNotFoundException( message = DATA_NOT_FOUND)
        }


        data.get().name = request.name
        data.get().email = request.email
        data.get().username = request.username
        data.get().password = request.password

        profileRepository.save(data.get())
        return ResMessageDto()
    }

    override fun detail(id: UUID): ResMessageDto<ResGetProfileDto> {
        // find and validated
        val data = profileRepository.findById(id)
        if(data.isEmpty){
            throw DataNotFoundException(DATA_NOT_FOUND)
        }

        //mapped data
        val mappedData  = ResGetProfileDto(
            id = data.get().id!!,
            name = data.get().name!!,
            username = data.get().username,
            email = data.get().email,
        )
        return ResMessageDto(data = mappedData)
    }

    override fun list(): ResMessageDto<List<ResGetProfileDto>> {
        val allData = profileRepository.findAll()
        val resData= arrayListOf<ResGetProfileDto>()
        for(dt in allData){
            val mappedData  = ResGetProfileDto(
                id = dt.id!!,
                name = dt.name!!,
                username = dt.username,
                email = dt.email,
            )
            resData.add(mappedData)
        }
        return ResMessageDto(data = resData)
    }

    override fun delete(id: UUID): ResMessageDto<String> {
        if(!profileRepository.existsById(id)){
            throw DataNotFoundException(DATA_NOT_FOUND)
        }
        profileRepository.deleteById(id)
        return ResMessageDto()
    }

    fun getAvatar(avatar : String) : String{
        when (avatar) {
            "male" ->  return svgProfileApiClient.getProfile("male")
            "female" -> return svgProfileApiClient.getProfile("female")
        }
        return ""
    }
}