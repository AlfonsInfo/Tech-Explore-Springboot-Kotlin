package learn.spring.kotlin.features.testing.message

import learn.spring.kotlin.features.testing.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(
    val messageRepository: MessageRepository
){



    @PostMapping("/message")
    fun postDummyMessage() {
        val data = listOf(
            MessageTable(1, "Hello!"),
            MessageTable(2, "Bonjour!"),
            MessageTable(3, "Privet!"),
        )
        messageRepository.saveAll(data)
    }
    @GetMapping("/message-table")
    fun showingFetchData(){

    }
}
