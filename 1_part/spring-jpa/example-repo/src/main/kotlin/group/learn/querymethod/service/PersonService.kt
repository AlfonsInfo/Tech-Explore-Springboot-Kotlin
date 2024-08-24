package group.learn.querymethod.service

import group.learn.querymethod.domain.entity.Person
import group.learn.querymethod.repository.PersonCRUDRepository
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val personBaseRepository: PersonCRUDRepository
) {
    fun createAutomatic() : Any?{
        val person = Person()
        person.id = 1
        person.name = "Alfons"
        personBaseRepository.save(person)
        return null
    }
}