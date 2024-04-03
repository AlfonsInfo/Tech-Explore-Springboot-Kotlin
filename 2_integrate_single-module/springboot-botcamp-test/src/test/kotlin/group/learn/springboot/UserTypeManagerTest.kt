package group.learn.springboot

import group.learn.springboot.domain.dto.request.ReqReadDto
import group.learn.springboot.domain.entity.UserTypeEntity
import group.learn.springboot.domain.manager.UserTypeManager
import jakarta.persistence.EntityManager
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserTypeManagerTest {
    @Autowired
    lateinit var entityManager: EntityManager

    @InjectMocks
    lateinit var userTypeManageRepository: UserTypeManager

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testTheLogic(){
        val queryString = StringBuilder("SELECT * FROM mst_user_type ut \n" +
                "LEFT JOIN mst_user u ON ut.id_type = u.id_type\n")
        val query = entityManager.createNativeQuery(queryString.toString())
        query.resultList.forEach {
            print(it)
        }
    }

    @Test
    fun testSearchAndFilter() {
//        // Given
//        val request = ReqReadDto()
//
//        val result: List<UserTypeEntity> = userTypeManageRepository.searchAndFilter(request)
//
//        println(result)
//        // Then
//        Assertions.assertEquals(0, result.size) // change 0 to the expected result size
    }

}