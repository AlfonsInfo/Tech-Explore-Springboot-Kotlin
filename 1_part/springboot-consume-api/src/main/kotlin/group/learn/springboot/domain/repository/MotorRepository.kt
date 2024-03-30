package group.learn.springboot.domain.repository

import group.learn.springboot.domain.entity.MotorEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MotorRepository : JpaRepository<MotorEntity, UUID> {
}