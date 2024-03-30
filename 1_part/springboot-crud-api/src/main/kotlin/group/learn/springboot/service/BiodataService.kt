package group.learn.springboot.service

import group.learn.springboot.domain.dto.response.ResPersonBaseDto
import group.learn.springboot.domain.dto.response.ResPersonWithAgeDto
import group.learn.springboot.domain.dto.response.ResPersonWithRoleDto
import org.springframework.stereotype.Service


interface BiodataService {
    fun getBaseBioData() : ResPersonBaseDto
    fun getBioWithAge(age : String) : ResPersonWithAgeDto
    fun getBioWithRole(role : String) : ResPersonWithRoleDto

}