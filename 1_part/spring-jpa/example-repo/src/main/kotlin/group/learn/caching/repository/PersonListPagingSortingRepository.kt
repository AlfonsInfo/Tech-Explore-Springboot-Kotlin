package group.learn.caching.repository

import group.learn.caching.domain.entity.Person
import org.springframework.data.repository.ListPagingAndSortingRepository


interface PersonListPagingSortingRepository : ListPagingAndSortingRepository<Person, Long> {
    //CRUD Repository

    
}

