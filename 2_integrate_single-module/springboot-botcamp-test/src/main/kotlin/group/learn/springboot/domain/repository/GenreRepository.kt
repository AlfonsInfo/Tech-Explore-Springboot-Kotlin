package group.learn.springboot.domain.repository

import group.learn.springboot.domain.entity.FavouriteEntity
import group.learn.springboot.domain.entity.GenreEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface GenreRepository : JpaRepository<GenreEntity, Int> {
}