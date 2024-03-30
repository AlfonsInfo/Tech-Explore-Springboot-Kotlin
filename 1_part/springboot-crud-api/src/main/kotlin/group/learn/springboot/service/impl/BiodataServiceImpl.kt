package group.learn.springboot.service.impl

import group.learn.springboot.domain.dto.response.ResPersonBaseDto
import group.learn.springboot.domain.dto.response.ResPersonWithAgeDto
import group.learn.springboot.domain.dto.response.ResPersonWithRoleDto
import group.learn.springboot.service.BiodataService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class BiodataServiceImpl (
    @Value("\${name.firstname}") private val firstname  : String? = null,
    @Value("\${name.lastname}") private val lastname  : String? = null,
): BiodataService {

    override fun getBaseBioData(): ResPersonBaseDto {
        return ResPersonBaseDto(firstname!!, lastname!!)
    }

    override fun getBioWithAge(age : String): ResPersonWithAgeDto {
        return ResPersonWithAgeDto(firstname!!, lastname!!,age)
    }

    override fun getBioWithRole(role : String): ResPersonWithRoleDto {
        return ResPersonWithRoleDto(firstname!!, lastname!!,role)
    }
}