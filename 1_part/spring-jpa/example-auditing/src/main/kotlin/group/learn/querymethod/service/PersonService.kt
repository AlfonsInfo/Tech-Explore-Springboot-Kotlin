package group.learn.querymethod.service

import group.learn.querymethod.entity.Person
import group.learn.querymethod.repository.PersonJpaRepository
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