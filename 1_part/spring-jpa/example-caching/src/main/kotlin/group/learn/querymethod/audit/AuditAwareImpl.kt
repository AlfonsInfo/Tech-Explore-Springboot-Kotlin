package group.learn.querymethod.audit

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
        val auditor = Optional.ofNullable(httpServletRequest.getHeader("idUser"))
        return auditor
    }

}