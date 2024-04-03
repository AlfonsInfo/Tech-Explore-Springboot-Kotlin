//package group.learn.springboot.config.resolver
//
//import group.learn.springboot.domain.entity.UserEntity
//import group.learn.springboot.domain.repository.UserRepository
//import org.springframework.core.MethodParameter
//import org.springframework.web.bind.support.WebDataBinderFactory
//import org.springframework.web.context.request.NativeWebRequest
//import org.springframework.web.method.support.HandlerMethodArgumentResolver
//import org.springframework.web.method.support.ModelAndViewContainer
//
//class UserArgumentResolver(
//    val userRepository: UserRepository
//) : HandlerMethodArgumentResolver {
//    override fun supportsParameter(parameter: MethodParameter): Boolean {
//        return UserEntity::class.java.equals(parameter.parameterType)
//    }
//
//    override fun resolveArgument(
//        parameter: MethodParameter,
//        mavContainer: ModelAndViewContainer?,
//        webRequest: NativeWebRequest,
//        binderFactory: WebDataBinderFactory?
//    ): Any? {
//        TODO("Not yet implemented")
//    }
//}