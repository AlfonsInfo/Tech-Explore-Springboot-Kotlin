package group.learn.springboot.config

import com.github.javafaker.Faker
import group.learn.springboot.domain.entity.GenreEntity
import group.learn.springboot.domain.entity.MusicEntity
import group.learn.springboot.domain.entity.UserEntity
import group.learn.springboot.domain.entity.UserTypeEntity
import group.learn.springboot.domain.repository.GenreRepository
import group.learn.springboot.domain.repository.MusicRepository
import group.learn.springboot.domain.repository.UserRepository
import group.learn.springboot.domain.repository.UserTypeRepository
import jakarta.annotation.PostConstruct
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DummyDataLoader(
    private val userRepository: UserRepository,
    private val userTypeRepository: UserTypeRepository,
    private val passwordEncoder: PasswordEncoder,
    private val genreRepository: GenreRepository,
    private val musicRepository: MusicRepository,
) {
    @PostConstruct
    fun loadDummy(){
        //* Dummy Type
        userTypeRepository.save(UserTypeEntity(name = "T001", desc = "Free"))
        userTypeRepository.save(UserTypeEntity(name = "T002", desc = "Premium"))

        val typeFree = userTypeRepository.findByName("T001").get()
        val typePremium = userTypeRepository.findByName("T002").get()

        val password = passwordEncoder.encode("password")
        //* Dummy User
        userRepository.save( UserEntity(username = "dummy1", email = "email1@gmail.com", type = typeFree, password = password))
        userRepository.save( UserEntity(username = "dummy2", email = "email2@gmail.com", type = typeFree, password = password))
        userRepository.save( UserEntity(username = "dummy3", email = "email3@gmail.com", type = typePremium, password = password))
        userRepository.save( UserEntity(username = "dummy4", email = "email4@gmail.com", type = typePremium, password = password))
        userRepository.save( UserEntity(username = "dummy5", email = "emailt@gmail.com", type = typePremium, password = password))

        //dummy genre music

        // Dummy data for music genres with description
        genreRepository.saveAll(
            listOf(
                GenreEntity(name = "Pop", desc = "Popular music characterized by catchy melodies and upbeat rhythms."),
                GenreEntity(name = "Rock", desc = "Genre of popular music that originated as 'rock and roll' in the United States in the 1950s, and developed into a range of different styles in the 1960s and later."),
                GenreEntity(name = "Hip Hop", desc = "Genre of popular music characterized by a rhythmic vocal style called rap."),
                GenreEntity(name = "Jazz", desc = "Musical genre that originated in the African-American communities of New Orleans, United States."),
                GenreEntity(name = "Electronic", desc = "Genre of music that is primarily created using electronic devices and technology.")
                // Add more genres with descriptions as needed
            )
        )

        genreRepository.findAll().forEach {
            musicRepository.save(
                MusicEntity(
                    type = if(randomBool()) typeFree else typePremium,
                    dtAdded = LocalDateTime.now(),
                    musicName = Faker().music().instrument()
                )
            )
        }
    }

    fun randomBool() : Boolean {
        return Math.random() > 0.5
    }
}