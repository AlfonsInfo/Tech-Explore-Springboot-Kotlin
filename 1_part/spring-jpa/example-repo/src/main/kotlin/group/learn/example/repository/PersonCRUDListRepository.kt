package group.learn.example.repository

import group.learn.example.domain.entity.Person
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository


@Repository
interface PersonCRUDListRepository : ListCrudRepository<Person,Long> {
    //LIST + CRUD Repository

}

