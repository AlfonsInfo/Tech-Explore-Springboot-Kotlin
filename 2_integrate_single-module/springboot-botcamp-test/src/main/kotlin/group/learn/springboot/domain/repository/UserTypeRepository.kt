package group.learn.springboot.domain.repository

import group.learn.springboot.domain.entity.FavouriteEntity
import group.learn.springboot.domain.entity.GenreEntity
import group.learn.springboot.domain.entity.UserTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID
@Repository
interface UserTypeRepository : JpaRepository<UserTypeEntity, UUID> {
    fun findByName(name : String) : Optional<UserTypeEntity>


}