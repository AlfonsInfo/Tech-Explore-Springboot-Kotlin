package group.learn.querymethod.repository

import group.learn.querymethod.domain.entity.Person
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PersonPagingSortingRepository : PagingAndSortingRepository<Person, UUID> {
    //CRUD Repository

}

