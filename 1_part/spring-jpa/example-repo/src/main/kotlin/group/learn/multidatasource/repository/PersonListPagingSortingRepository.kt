package group.learn.multidatasource.repository

import group.learn.multidatasource.domain.entity.Person
import org.springframework.data.repository.ListPagingAndSortingRepository


interface PersonListPagingSortingRepository : ListPagingAndSortingRepository<Person, Long> {
    //CRUD Repository

    
}

