package group.learn.springboot.config

import group.learn.springboot.config.interceptor.ApiKeyInterceptor
import group.learn.springboot.config.interceptor.AuthKeyInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
@Configuration
class InterceptorConfiguration (
    val apiKeyInterceptor: ApiKeyInterceptor,
    val authKeyInterceptor: AuthKeyInterceptor,
) : WebMvcConfigurer{

    override fun addInterceptors(registry: InterceptorRegistry) {
        val excludedPaths = listOf(
            "/swagger-ui/**",
            "/actuator/**",
            "/v1/docs/**"
        )

        registry
            .addInterceptor(apiKeyInterceptor)
            .excludePathPatterns(excludedPaths)

        registry
            .addInterceptor(authKeyInterceptor)
            .excludePathPatterns(excludedPaths)
            .excludePathPatterns("/api/v1/auth/login")
            .excludePathPatterns("/api/v1/user/register")
    }
}