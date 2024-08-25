package group.learn.multidatasource.repository

import group.learn.multidatasource.domain.entity.Person
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository


@Repository
interface PersonCRUDListRepository : ListCrudRepository<Person,Long> {
    //LIST + CRUD Repository

}

