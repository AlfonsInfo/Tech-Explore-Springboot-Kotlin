package group.learn.springboot.service

import group.learn.springboot.domain.dto.request.ReqLoginDto
import group.learn.springboot.domain.dto.response.ResLoginDto

interface AuthSessionService {

    fun login(request : ReqLoginDto) : ResLoginDto?
}