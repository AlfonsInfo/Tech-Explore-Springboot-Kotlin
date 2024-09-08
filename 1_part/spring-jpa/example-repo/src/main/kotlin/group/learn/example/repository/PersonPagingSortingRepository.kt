package group.learn.example.repository

import group.learn.example.domain.entity.Person
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PersonPagingSortingRepository : PagingAndSortingRepository<Person, UUID> {
    //CRUD Repository

}

