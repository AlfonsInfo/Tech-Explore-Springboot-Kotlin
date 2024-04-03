package group.learn.springboot.domain.repository

import group.learn.springboot.domain.entity.FavouriteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID


@Repository
interface FavouriteRepository : JpaRepository<FavouriteEntity, UUID> {

    fun findAllByUserAdded(userAdded : String) : List<FavouriteEntity>
}