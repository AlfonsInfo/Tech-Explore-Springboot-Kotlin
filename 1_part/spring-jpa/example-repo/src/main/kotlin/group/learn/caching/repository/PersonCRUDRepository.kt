package group.learn.caching.repository

import group.learn.caching.domain.entity.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PersonCRUDRepository : CrudRepository<Person, UUID> {
    //CRUD Repository
}

