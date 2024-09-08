package group.learn.example.repository

import group.learn.example.domain.entity.Person
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonReactiveRepository : ReactiveCrudRepository<Person, Long>