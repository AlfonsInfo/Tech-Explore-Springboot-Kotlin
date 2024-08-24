package group.learn.querymethod.repository

import group.learn.querymethod.domain.entity.Person
import org.springframework.data.repository.Repository
import org.springframework.stereotype.Component
import java.util.*


//Repository has no implementation
@org.springframework.stereotype.Repository
@Component
interface PersonBaseRepository : Repository<Person,Long> {
    //Repository has no method
    // ctrl + insert check method
    // ctrl + shift + h check hierarchy
    fun save(person: Person?): Person?

    fun findById(id: Long): Optional<Person?>?
}

