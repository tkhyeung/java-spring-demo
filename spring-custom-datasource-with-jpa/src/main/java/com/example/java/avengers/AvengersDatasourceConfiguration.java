package com.example.java.avengers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@Profile("avengers")
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.java",
        entityManagerFactoryRef = "avengersEntityManager",
        transactionManagerRef = "avengersTransactionManager"
)
public class AvengersDatasourceConfiguration {

    @Bean(name = "avengersDataSourceProperties")
    @ConfigurationProperties("avengers.datasource")
    public DataSourceProperties avengersDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "avengersDataSource")
    public DataSource avengersDataSource(
            @Qualifier("avengersDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder()
                .build();
    }

    @Bean(name = "avengersEntityManager")
    public LocalContainerEntityManagerFactoryBean memberEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("avengersDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.java.entity")
                .persistenceUnit("avengers").build();
    }

    @Bean(name = "avengersTransactionManager")
    public PlatformTransactionManager avengersTransactionManager(
            final @Qualifier("avengersEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
