package group.learn.example.service

import group.learn.example.entity.Person
import group.learn.example.repository.PersonJpaRepository
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val personBaseRepository: PersonJpaRepository
) {
    fun createAutomatic() : Any?{
        val person = Person()
        person.id = 1
        person.name = "Alfons"
        personBaseRepository.save(person)
        return null
    }
}