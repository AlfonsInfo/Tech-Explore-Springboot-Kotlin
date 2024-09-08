package group.learn.example.common.jpa

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.global")
    fun dataSourceOneProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.tenant")
    fun dataSourceTwoProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    fun dataSourceOne(): DataSource {
        val properties = dataSourceOneProperties()
        return DriverManagerDataSource().apply {
            url = properties.url
            username = properties.username
            password = properties.password
        }
    }



    @Bean
    fun dataSourceTwo(): DataSource {
        val properties = dataSourceTwoProperties()
        return DriverManagerDataSource().apply {
            url = properties.url
            username = properties.username
            password = properties.password
        }
    }
}
