package group.learn.example.repository

import group.learn.example.domain.entity.Person
import org.springframework.data.repository.ListPagingAndSortingRepository


interface PersonListPagingSortingRepository : ListPagingAndSortingRepository<Person, Long> {
    //CRUD Repository

    
}

