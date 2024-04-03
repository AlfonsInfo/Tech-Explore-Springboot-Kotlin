package group.learn.springboot.domain.repository

import group.learn.springboot.domain.entity.FavouriteEntity
import group.learn.springboot.domain.entity.GenreEntity
import group.learn.springboot.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun findByUsername(username : String) : Optional<UserEntity>
    fun findByUsernameAndPassword(username : String, password : String) : Optional<UserEntity>
}
