    package group.learn.multidatasource.common.aspect

    import org.aspectj.lang.ProceedingJoinPoint
    import org.aspectj.lang.annotation.After
    import org.aspectj.lang.annotation.Around
    import org.aspectj.lang.annotation.Aspect
    import org.aspectj.lang.annotation.Pointcut
    import org.springframework.stereotype.Component

    @Aspect
    @Component
    class QueryCountAspect {

        private var queryCount = 0

        @Pointcut("execution(* org.springframework.data.jpa.repository.JpaRepository+.*(..))")
        fun repositoryMethods() {}

        @Pointcut("execution(* group.learn.querymethod..service..*(..))")
        fun serviceMethod() {
        }

        @Around("repositoryMethods()")
        fun logAroundRepositoryMethods(joinPoint: ProceedingJoinPoint): Any? {
            val result = joinPoint.proceed()
            println("Number of queries executed: $queryCount")
            return result
        }

        @After("repositoryMethods()")
        fun incrementQueryCount() {
            queryCount++
        }

        @After("serviceMethod()")
        fun reset() {
            queryCount = 0
        }
    }