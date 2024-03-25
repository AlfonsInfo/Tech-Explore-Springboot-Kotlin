package learn.spring.kotlin.features.testing.message

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class MessageDataTable(@Id val id  : String , val text : String)
