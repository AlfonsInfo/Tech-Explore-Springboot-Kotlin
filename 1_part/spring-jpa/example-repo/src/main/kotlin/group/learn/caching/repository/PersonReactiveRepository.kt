package group.learn.caching.repository

import group.learn.caching.domain.entity.Person
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonReactiveRepository : ReactiveCrudRepository<Person, Long> {
}