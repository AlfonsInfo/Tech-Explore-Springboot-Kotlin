package group.learn.multidatasource.repository

import group.learn.multidatasource.domain.entity.Person
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PersonPagingSortingRepository : PagingAndSortingRepository<Person, UUID> {
    //CRUD Repository

}

