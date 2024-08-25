package group.learn.multidatasource.repository

import group.learn.multidatasource.domain.entity.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PersonCRUDRepository : CrudRepository<Person, UUID> {
    //CRUD Repository
}

