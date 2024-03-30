package learn.spring.kotlin.features.testing.repository

//import learn.spring.kotlin.features.testing.message.MessageDataTable
import learn.spring.kotlin.features.testing.message.MessageTable
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository : JpaRepository<MessageTable, String> {

}