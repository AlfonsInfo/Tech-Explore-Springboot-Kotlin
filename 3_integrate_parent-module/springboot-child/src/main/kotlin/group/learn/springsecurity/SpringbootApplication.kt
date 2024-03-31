package group.learn.springsecurity

//import group.learn.springboot.rest.DogApiClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients	(basePackages = ["group.learn.springboot.openfeignclient"])
class SpringbootApplication

fun main(args: Array<String>) {
	runApplication<SpringbootApplication>(*args)
}
