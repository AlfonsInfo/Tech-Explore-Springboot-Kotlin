package group.learn.querymethod.repository

import group.learn.querymethod.domain.entity.Person
import org.springframework.data.repository.ListPagingAndSortingRepository


interface PersonListPagingSortingRepository : ListPagingAndSortingRepository<Person, Long> {
    //CRUD Repository

    
}

