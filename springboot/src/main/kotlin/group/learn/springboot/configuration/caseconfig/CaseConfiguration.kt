package group.learn.springboot.configuration.caseconfig

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean

class CaseConfiguration {

    @Bean
    fun objectMapper() : ObjectMapper {
        val mapper = ObjectMapper()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
        mapper.setPropertyNamingStrategy(ScreamingSnakeCaseStrategy())
        return mapper
    }

}