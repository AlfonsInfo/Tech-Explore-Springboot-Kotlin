package group.learn.example.service

import group.learn.example.domain.entity.Person
import group.learn.example.repository.PersonCRUDRepository
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