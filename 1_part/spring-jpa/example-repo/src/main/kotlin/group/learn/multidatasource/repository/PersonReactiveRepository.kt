package group.learn.multidatasource.repository

import group.learn.multidatasource.domain.entity.Person
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonReactiveRepository : ReactiveCrudRepository<Person, Long>