package group.learn.multidatasource
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@ComponentScan
class ExAuditingApplication

fun main(args: Array<String>) {
	runApplication<ExAuditingApplication>(*args)
}
