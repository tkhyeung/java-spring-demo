package com.example.java.eternals;

import com.zaxxer.hikari.HikariDataSource;
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
@Profile("eternals")
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.java",
        entityManagerFactoryRef = "eternalsEntityManager",
        transactionManagerRef = "eternalsTransactionManager"
)
public class EternalsDatasourceConfiguration {

    @Bean(name = "eternalsDataSourceProperties")
    @ConfigurationProperties("eternals.datasource")
    public DataSourceProperties eternalsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "eternalsDataSource")
    public DataSource eternalsDataSource(
            @Qualifier("eternalsDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder()
                .build();
    }

    @Bean(name = "eternalsEntityManager")
    public LocalContainerEntityManagerFactoryBean memberEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("eternalsDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.java.entity")
                .persistenceUnit("eternals").build();
    }

    @Bean(name = "eternalsTransactionManager")
    public PlatformTransactionManager eternalsTransactionManager(
            final @Qualifier("eternalsEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
