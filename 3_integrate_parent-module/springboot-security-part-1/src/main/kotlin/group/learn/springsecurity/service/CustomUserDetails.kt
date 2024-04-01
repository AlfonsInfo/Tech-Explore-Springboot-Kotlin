package group.learn.springsecurity.service

import group.learn.springsecurity.domain.entity.Customer
import group.learn.springsecurity.domain.repository.CustomerRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class CustomUserDetails(
    val customerRepository: CustomerRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val authorities: MutableList<GrantedAuthority>
        val customer: List<Customer> = customerRepository.findByEmail(username!!)
        var userName: String = ""
        var password: String = ""
        if (customer.isEmpty()) {
            throw UsernameNotFoundException("User details not found for the user : $username")
        } else {
            userName = customer[0].name!!
            password = customer[0].password!!
            authorities = ArrayList()
            //authorities.add(SimpleGrantedAuthority(customer[0].))
        }
        //* implementasi dari user detail
        return User(userName, password, authorities)
    }
}