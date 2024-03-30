package group.learn.springboot.domain.repository


import group.learn.springboot.domain.entity.ProfileEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProfileRepository : JpaRepository<ProfileEntity, UUID> {
}