package group.learn.springboot.config

import group.learn.springboot.utils.LoggingUtils
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


@Aspect
@Component
class LoggingAspectConfiguration(
    private val loggingUtils: LoggingUtils
) {
    private val log = LoggerFactory.getLogger(LoggingAspectConfiguration::class.java)

    @Pointcut("execution(* group.learn.springboot..service..*(..))")
    fun serviceMethod() {
    }


    @Before("serviceMethod()")
    fun logBeforeCall(joinPoint: JoinPoint) {
        val className = joinPoint.target.javaClass.getSimpleName()
        val methodName = joinPoint.signature.name
        log.info(loggingUtils.logFunction(className, methodName, "START POSITION"))
    }

    @After("serviceMethod()")
    fun logAfterCall(joinPoint: JoinPoint) {
        val className = joinPoint.target.javaClass.getSimpleName()
        val methodName = joinPoint.signature.name
        log.info(loggingUtils.logFunction(className, methodName, "AFTER EXECUTION"))
    }

}