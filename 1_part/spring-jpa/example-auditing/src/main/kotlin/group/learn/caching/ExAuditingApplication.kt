package group.learn.caching
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
class ExAuditingApplication

fun main(args: Array<String>) {
	runApplication<ExAuditingApplication>(*args)
}