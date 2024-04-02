package group.learn.springboot.config

import group.learn.springboot.service.impl.LogicService
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.util.Collections

@Component
class OpenApiConfiguration (
    private val logicService: LogicService
){

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .components(
                Components()
                    .addSecuritySchemes("apiKey", apiKey())
                    .addSecuritySchemes("token", jwtToken())
            )
            .security(Collections.singletonList(securityRequirement()))
    }

    private fun apiKey(): SecurityScheme {
        return SecurityScheme()
            .type(SecurityScheme.Type.APIKEY)
            .`in`(SecurityScheme.In.HEADER)
            .name("API-KEY")
            .description("Global API Key for authentication")
        // Optionally set required: true
    }

    private fun jwtToken(): SecurityScheme {
        return SecurityScheme()
            .type(SecurityScheme.Type.APIKEY)
            .`in`(SecurityScheme.In.HEADER)
            .name("API-KEY")
            .description("Global API Key for authentication")
        // Optionally set required: true
    }


    private fun securityRequirement(): SecurityRequirement {
        return SecurityRequirement().addList("apiKey")
    }

}