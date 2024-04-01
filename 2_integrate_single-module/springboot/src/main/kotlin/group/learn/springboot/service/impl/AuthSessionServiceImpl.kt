package group.learn.springboot.service.impl

import group.learn.springboot.domain.dto.request.ReqEncodeJwtDto
import group.learn.springboot.domain.dto.request.ReqLoginDto
import group.learn.springboot.domain.dto.response.ResLoginDto
import group.learn.springboot.domain.repository.ProfileRepository
import group.learn.springboot.service.AuthSessionService
import group.learn.springboot.utils.JwtGenerator
import org.springframework.stereotype.Service

@Service
class AuthSessionServiceImpl(
    val profileRepository: ProfileRepository
) : AuthSessionService {
    override fun login(request: ReqLoginDto): ResLoginDto? {
        val data = profileRepository.findByUsernameAndPassword(request.username, request.password)
        if(data.isNotEmpty()){
            val userDetail = data[0]
            val reqJwt = ReqEncodeJwtDto(
                id = userDetail.id.toString(),
                name = userDetail.name!!,
                email  = userDetail.email!!,
                username = userDetail.username!!,
                role  = "",
                password  = userDetail.password!!
            )
            val token = JwtGenerator().createJWT(reqJwt)
            return ResLoginDto(userDetail.id.toString(), token)
        }else{
            return null
        }
    }
}