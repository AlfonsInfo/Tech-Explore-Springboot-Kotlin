package group.learn.springboot.configuration.caseconfig

import com.fasterxml.jackson.databind.DeserializationFeature
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy

@Configuration
class ScreamingSnakeCaseStrategy() : PropertyNamingStrategy.SnakeCaseStrategy() {

    override fun translate(input: String?): String {
        return super.translate(input).uppercase()
    }
}