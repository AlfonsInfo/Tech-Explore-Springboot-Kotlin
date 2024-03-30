package group.learn.springboot.service.impl

import group.learn.springboot.domain.dto.request.ReqUpsertDto
import group.learn.springboot.domain.dto.response.ResMessageDto
import group.learn.springboot.domain.entity.MotorEntity
import group.learn.springboot.service.MotorServiceManagerService
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MotorServiceManagerImpl  : MotorServiceManagerService{
    @Autowired
    lateinit var entityManager: EntityManager

    @Transactional
    override fun saveMotor(request: ReqUpsertDto): ResMessageDto<String> {
        val data = MotorEntity(name = request.name, merk = request.merk)
        entityManager.persist(data)
        return ResMessageDto()
    }
}