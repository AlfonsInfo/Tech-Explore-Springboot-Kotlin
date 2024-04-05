package group.learn.springboot.service.impl

import group.learn.springboot.domain.dto.request.ReqInsertUserDto
import group.learn.springboot.domain.dto.request.ReqUpdateUserDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.domain.dto.response.ResUserDto
import group.learn.springboot.domain.entity.UserEntity
import group.learn.springboot.domain.repository.UserRepository
import group.learn.springboot.domain.repository.UserTypeRepository
import group.learn.springboot.service.UserService
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*


@Service
class UserServiceImpl  (
    private val userRepository: UserRepository,
    private val userTypeRepository: UserTypeRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService{
    @Transactional
    override fun insert(request : ReqInsertUserDto) : BaseResponse<String>{
        //*
            val type = userTypeRepository.findByName(request.idType).orElseThrow{
            ResponseStatusException(HttpStatus.NOT_FOUND, "Type Not Found")
        }

        //* inserting data
        val user = UserEntity(
            username = request.username,
            email = request.email,
            password =  passwordEncoder.encode(request.password),
            type = type
        )
        userRepository.save(user)
        return BaseResponse()
    }

    override fun getAll(): BaseResponse<List<ResUserDto>> {
        val users =  userRepository.findAll()
        val dataForResp =  mutableListOf<ResUserDto>()
        users.forEach {entity ->
            val data = ResUserDto(
                username = entity.username,
                email = entity.email,
                type = entity.type?.name
            )
            dataForResp.add(data)
        }
        return BaseResponse(data =  dataForResp)
    }

    override fun find(request: String): BaseResponse<ResUserDto> {
        val user = userRepository.findById(UUID.fromString(request)).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
        }

        val dataForResp = ResUserDto()
        dataForResp.username = user.username
        dataForResp.email = user.email
        dataForResp.type = user.type?.name
        return BaseResponse(data = dataForResp)
    }

    override fun update(id: String,request: ReqUpdateUserDto): BaseResponse<String> {
        val dataForUpdate = userRepository.findById(UUID.fromString(id)).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
        }

        val userType = userTypeRepository.findByName(request.userType).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User Type Not Found")
        }
        dataForUpdate.type = userType
        userRepository.save(dataForUpdate)
        return BaseResponse()
    }
}