package group.learn.springboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
//tema : lagu
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class SpringbootApplication

fun main(args: Array<String>) {
	runApplication<SpringbootApplication>(*args)
}

