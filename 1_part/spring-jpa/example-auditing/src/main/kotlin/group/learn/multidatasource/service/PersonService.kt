package group.learn.multidatasource.service

import group.learn.multidatasource.entity.Person
import group.learn.multidatasource.repository.PersonJpaRepository
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