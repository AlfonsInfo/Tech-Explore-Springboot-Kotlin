package group.learn.caching.service

import group.learn.caching.entity.Person
import group.learn.caching.repository.PersonJpaRepository
import org.springframework.data.repository.CrudRepository
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