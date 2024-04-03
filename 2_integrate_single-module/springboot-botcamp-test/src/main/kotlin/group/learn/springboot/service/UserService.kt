package group.learn.springboot.service

import group.learn.springboot.domain.dto.request.ReqInsertUserDto
import group.learn.springboot.domain.dto.request.ReqUpdateUserDto
import group.learn.springboot.domain.dto.response.BaseResponse
import group.learn.springboot.domain.dto.response.ResUserDto

interface UserService {
    fun insert(request : ReqInsertUserDto) : BaseResponse<String>
    fun getAll() : BaseResponse<List<ResUserDto>>

    fun find(request : String) : BaseResponse<ResUserDto>

    fun update(id : String , request : ReqUpdateUserDto) :  BaseResponse<String>

    //* delete
}