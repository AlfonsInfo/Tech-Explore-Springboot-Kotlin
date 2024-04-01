package group.learn.springsecurity.domain.repository

import group.learn.springsecurity.domain.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface CustomerRepository : JpaRepository<Customer, UUID> {
    fun findByEmail(email : String) : List<Customer>
}