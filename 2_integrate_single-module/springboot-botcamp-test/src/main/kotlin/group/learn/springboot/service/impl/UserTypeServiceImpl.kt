package group.learn.springboot.service.impl

import group.learn.springboot.constants.KeyValueConstants
import group.learn.springboot.domain.dto.request.ReqReadDto
import group.learn.springboot.domain.dto.request.ReqUpsertUserTypeDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.domain.dto.response.ResReadDetailUser
import group.learn.springboot.domain.dto.response.ResReadUserTypeDto
import group.learn.springboot.domain.entity.UserTypeEntity
import group.learn.springboot.domain.manager.UserTypeManager
import group.learn.springboot.domain.repository.UserTypeRepository
import group.learn.springboot.service.UserTypeService
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*


@Service
class UserTypeServiceImpl(
    private val userTypeRepository: UserTypeRepository,
    private val userTypeManageRepository: UserTypeManager
) : UserTypeService{
    override fun create(request: ReqUpsertUserTypeDto): BaseResponse<String> {
        //* mapping and insert data
        val userType = UserTypeEntity()
        userType.name = request.name
        userType.desc = request.desc
        userTypeRepository.save(userType)
        return BaseResponse(data = userType.id.toString())
    }

    override fun read(request: ReqReadDto): BaseResponse<List<ResReadUserTypeDto>> {
        //* find all data
        val dataFromQuery = userTypeRepository.findAll()
        //container
        val dataForResp = mutableListOf<ResReadUserTypeDto>()
        //mapping data
        dataFromQuery.forEach{entity ->
            val data = ResReadUserTypeDto()
            data.id = entity.id.toString()
            data.name = entity.name!!
            data.desc= entity.desc!!
            checkModeAndSetUser(request, entity, data)
            dataForResp.add(data)
        }
        return BaseResponse(data = dataForResp)
    }


    override fun readDetail(id: String, request: ReqReadDto): BaseResponse<ResReadUserTypeDto> {
        //find data
        val dataFromQuery = userTypeRepository.findById(UUID.fromString(id))
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND,"Type Not Found")}

        //mapping data
        val dataForResp = ResReadUserTypeDto()
        dataForResp.id = dataFromQuery.id.toString()
        dataForResp.name = dataFromQuery.name!!
        dataForResp.desc= dataFromQuery.desc!!
        checkModeAndSetUser(request,dataFromQuery,dataForResp)

        return BaseResponse(data = dataForResp)
    }



    @Transactional
    override fun update(id : String , request: ReqUpsertUserTypeDto): BaseResponse<String> {
        //* find data for update
        val userType = userTypeRepository
            .findById(UUID.fromString(id))
            .orElseThrow{
                ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "User Type Not Found"
                )
            }
        // update data
        userType.name = request.name
        userType.desc = request.desc
        userTypeRepository.save(userType)
        return BaseResponse(data = userType.id.toString())
    }

    override fun delete(id : String): BaseResponse<String> {
        //find data
        val userType = userTypeRepository
            .findById(UUID.fromString(id))
            .orElseThrow{
                ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "User Type Not Found"
                )
            }
        // handle type with user exist
        if(Objects.nonNull(userType.users) && userType.users!!.isNotEmpty()){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not deleted, currently there are users on this type")
        }
        //deleting
        userTypeRepository.deleteById(UUID.fromString(id))
        return BaseResponse()
    }

    private fun checkModeAndSetUser(
        request: ReqReadDto,
        entity: UserTypeEntity,
        data: ResReadUserTypeDto
    ) {
        if (Objects.equals(request.mode, KeyValueConstants.MODE_VALUE_COMPLETE)) {
            val listUserDetail = mutableListOf<ResReadDetailUser>()
            entity.users?.forEach {
                val detailUser = ResReadDetailUser()
                detailUser.id = it.id
                detailUser.username = it.username
                detailUser.email = it.email

                listUserDetail.add(detailUser)
            }
            data.user = listUserDetail
        }
    }
}