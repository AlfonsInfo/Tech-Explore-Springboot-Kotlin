package group.learn.springsecurity.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager

// User Management
@Configuration
class UserManagerConfiguration {

    /*
    UserDetailManager interfaces An extension of the UserDetailsService
    which provides the ability to create new users and update existing ones.
    implementasi : InMemoryUserDetailManager, JdbcUserDetailManager ,LdapUserDetailManager
    */
    @Bean
    fun users() : InMemoryUserDetailsManager{
        val admin = User
            .withDefaultPasswordEncoder()
            .username("admin1")
            .password("password")
            .roles("admin")
            .build()

        val kasir = User
            .withDefaultPasswordEncoder()
            .username("kasir1")
            .password("password")
            .roles("kasir")
            .build()

        return InMemoryUserDetailsManager(admin,kasir)
    }

}