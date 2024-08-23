package group.learn.caching.repository

import group.learn.caching.entity.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonJpaRepository : JpaRepository<Person, Long> {
    // ListCrudRepository<T, ID>, ListPagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T>

}

