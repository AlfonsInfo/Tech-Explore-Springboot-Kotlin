package group.learn.springboot.domain.manager

import group.learn.springboot.domain.dto.request.ReqReadDto
import group.learn.springboot.domain.entity.UserTypeEntity
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class UserTypeManager {

    @Autowired
    lateinit var entityManager: EntityManager

    fun searchAndFilter(request : ReqReadDto) : List<UserTypeEntity>{
        val queryString = StringBuilder("SELECT * FROM mst_user_type ut \n" +
                "LEFT JOIN mst_user u ON ut.id_type = u.id_type\n")
        val query = entityManager.createNativeQuery(queryString.toString())
        return query.resultList as List<UserTypeEntity>
    }
}