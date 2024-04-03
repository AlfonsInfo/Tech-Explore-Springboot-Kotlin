package group.learn.springboot.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectMapperConfiguration {

    fun objectMapper() : ObjectMapper{
        return ObjectMapper()
    }

}