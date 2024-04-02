package group.learn.springboot.config

import group.learn.springboot.config.interceptor.AuthInterceptor
import group.learn.springboot.config.interceptor.RequestInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class PathMatchingInterceptor(
    val authInterceptor: AuthInterceptor,
    val requestInterceptor: RequestInterceptor
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(requestInterceptor)
            .excludePathPatterns("/swagger-ui/**")
            .excludePathPatterns("/actuator/**")
            .excludePathPatterns("/v1/docs/**")

        registry.addInterceptor(authInterceptor)
            .excludePathPatterns("/jwt/encode")
            .excludePathPatterns("/auth/login")
            .excludePathPatterns("/swagger-ui/**")
            .excludePathPatterns("/actuator/**")
            .excludePathPatterns("/v1/docs/**")
    }
}