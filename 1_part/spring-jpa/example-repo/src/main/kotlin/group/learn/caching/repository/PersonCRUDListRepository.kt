package group.learn.caching.repository

import group.learn.caching.domain.entity.Person
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository


@Repository
interface PersonCRUDListRepository : ListCrudRepository<Person,Long> {
    //LIST + CRUD Repository

}

