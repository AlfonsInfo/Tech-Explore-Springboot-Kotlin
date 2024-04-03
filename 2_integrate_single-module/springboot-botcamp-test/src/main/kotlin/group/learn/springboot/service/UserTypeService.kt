package group.learn.springboot.service

import group.learn.springboot.domain.dto.request.ReqReadDto
import group.learn.springboot.domain.dto.request.ReqUpsertFavDto
import group.learn.springboot.domain.dto.request.ReqUpsertUserTypeDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.domain.dto.response.ResReadUserTypeDto
import jakarta.servlet.http.HttpServletRequest

interface UserTypeService {

    fun create(request : ReqUpsertUserTypeDto) : BaseResponse<String>

    fun read( request : ReqReadDto) : BaseResponse<List<ResReadUserTypeDto>>
    fun readDetail(id : String, request: ReqReadDto) : BaseResponse<ResReadUserTypeDto>

    fun update(id : String , request : ReqUpsertUserTypeDto) : BaseResponse<String>

    fun delete(id : String) : BaseResponse<String>
}