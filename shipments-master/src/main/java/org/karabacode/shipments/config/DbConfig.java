package org.karabacode.shipments.config;


import org.karabacode.shipments.credentials.CredencialesService;
import org.karabacode.shipments.credentials.VaultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = {"org.karabacode.shipments.repositories"})
public class DbConfig {

    @Autowired
    private CredencialesService credencialesService;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        VaultResponse vaultResponse = credencialesService.accessCredentials();
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        String databaseUsername = vaultResponse.getData().getUsername();
        String databasePassword = vaultResponse.getData().getPassword();
        dataSourceBuilder.password(databasePassword);
        dataSourceBuilder.username(databaseUsername);
        dataSourceBuilder.url(databaseUrl);
        dataSourceBuilder.driverClassName(driverClassName);
        return dataSourceBuilder.build();
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("org.karabacode.shipments.entities")
                .persistenceUnit("shipments")
                .build();
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManager);
        return transactionManager;
    }
}
