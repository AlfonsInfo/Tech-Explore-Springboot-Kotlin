package group.learn.springboot.domain.repository


import group.learn.springboot.domain.entity.MusicEntity
import group.learn.springboot.domain.entity.UserTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID


@Repository
interface MusicRepository : JpaRepository<MusicEntity, UUID> {

    @Query("SELECT m FROM MusicEntity m WHERE m.musicName LIKE CONCAT('%', :search, '%') OR m.lyric LIKE CONCAT('%', :search, '%') AND m.type.id = :typeId")
    fun findBySearchAndTypeId(@Param("search") search: String, @Param("typeId") typeId: UUID): List<MusicEntity>
    @Query("SELECT m FROM MusicEntity m WHERE m.musicName LIKE CONCAT('%', :search, '%') OR m.lyric LIKE CONCAT('%', :search, '%') ")
    fun findBySearch(@Param("search") search: String): List<MusicEntity>


    @Query("SELECT m.idGenre FROM MusicEntity m")
    fun findAllGenres(): List<String>

    fun findAllByIdGenreContains(genreId: Int): List<MusicEntity>
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM MusicEntity m WHERE m.idGenre LIKE CONCAT('%', :idGenre, '%')")
    fun existsByGenreIdContains(idGenre: String): Boolean

    fun findByMusicNameOrIdGenreContaining(musicName: String, idGenre: String): List<MusicEntity>
    fun findByMusicNameOrIdGenreOrTypeContaining(musicName: String, idGenre: String, idType: UserTypeEntity): List<MusicEntity>
}