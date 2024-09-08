package group.learn.example.common.jpa

import jakarta.persistence.EntityManagerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["group.learn.multidatasource.repository.global"],
    entityManagerFactoryRef = "entityManagerFactoryOne",
    transactionManagerRef = "transactionManagerOne"
)
class DataSourceOneConfig {

    @Bean
    fun entityManagerFactoryOne(dataSourceOne: DataSource): LocalContainerEntityManagerFactoryBean {
        val factory = LocalContainerEntityManagerFactoryBean()
        factory.dataSource = dataSourceOne
        factory.jpaVendorAdapter = HibernateJpaVendorAdapter()
        factory.setPackagesToScan("group.learn.multidatasource.domain.entity.global")
        return factory
    }

    @Bean
    fun transactionManagerOne(entityManagerFactoryOne: EntityManagerFactory): JpaTransactionManager {
        return JpaTransactionManager(entityManagerFactoryOne)
    }
}

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
    basePackages = ["group.learn.multidatasource.repository.tenant"],
    entityManagerFactoryRef = "entityManagerFactoryTwo",
    transactionManagerRef = "transactionManagerTwo"
)
class DataSourceTwoConfig {

    @Bean
    fun entityManagerFactoryTwo(dataSourceTwo: DataSource): LocalContainerEntityManagerFactoryBean {
        val factory = LocalContainerEntityManagerFactoryBean()
        factory.dataSource = dataSourceTwo
        factory.jpaVendorAdapter = HibernateJpaVendorAdapter()
        factory.setPackagesToScan("group.learn.multidatasource.domain.entity.tenant")
        return factory
    }

    @Bean
    fun transactionManagerTwo(entityManagerFactoryTwo: EntityManagerFactory): JpaTransactionManager {
        return JpaTransactionManager(entityManagerFactoryTwo)
    }
}
