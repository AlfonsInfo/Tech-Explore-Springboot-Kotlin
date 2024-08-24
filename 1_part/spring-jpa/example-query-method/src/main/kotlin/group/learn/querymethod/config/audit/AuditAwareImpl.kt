package group.learn.querymethod.config.audit

import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*


@Component
class AuditAwareImpl(
    private val httpServletRequest: HttpServletRequest
) :  AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        //scenario auditor from header
        try {
            return Optional.ofNullable(httpServletRequest.getHeader("idUser"))
        } catch (e: Exception) {
            return Optional.of("system")
        }
    }

}