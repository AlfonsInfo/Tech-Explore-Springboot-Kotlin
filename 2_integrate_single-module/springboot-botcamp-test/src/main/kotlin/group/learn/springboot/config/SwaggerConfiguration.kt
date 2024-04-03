    package group.learn.springboot.config

    import group.learn.springboot.constants.SecurityConstants
    import io.swagger.v3.oas.models.Components
    import io.swagger.v3.oas.models.OpenAPI
    import io.swagger.v3.oas.models.security.SecurityRequirement
    import io.swagger.v3.oas.models.security.SecurityScheme
    import org.springframework.context.annotation.Bean
    import org.springframework.stereotype.Component
    import java.util.Collections

    @Component
    class SwaggerConfiguration {
        @Bean
        fun openAPI(): OpenAPI {
            return OpenAPI()
                .components(
                    Components()
                        .addSecuritySchemes(SecurityConstants.PARAMS_API_KEY, apiKey())
                        .addSecuritySchemes(SecurityConstants.PARAMS_TOKEN, jwtToken())

                )
                .security(Collections.singletonList(securityRequirement()))
        }

        private fun apiKey() : SecurityScheme{
            return SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .`in`(SecurityScheme.In.HEADER)
                .name(SecurityConstants.PARAMS_API_KEY)
                .description("Global Api key, this api needed for all request")
        }

        private fun jwtToken(): SecurityScheme {
            return SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .`in`(SecurityScheme.In.HEADER)
                .name(SecurityConstants.PARAMS_TOKEN)
                .description("JWT Token")
        }




        private fun securityRequirement(): SecurityRequirement {
            return SecurityRequirement().addList(
                SecurityConstants.PARAMS_API_KEY
            ).addList(SecurityConstants.PARAMS_TOKEN)
        }
    }