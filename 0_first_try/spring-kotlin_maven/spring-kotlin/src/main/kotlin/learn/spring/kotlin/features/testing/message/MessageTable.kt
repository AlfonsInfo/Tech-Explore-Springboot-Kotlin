package learn.spring.kotlin.features.testing.message

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Entity
class MessageTable(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Int?= 0,
    @Column(nullable = false)
    val name : String=""
)
