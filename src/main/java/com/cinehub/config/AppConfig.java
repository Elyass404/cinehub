package com.cinehub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.cinehub")
public class AppConfig {

    // --- 1. Data Source (Connection details to the H2 database) ---

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // --- 1. MySQL Driver Class ---
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // --- 2. MySQL Connection URL ---
        dataSource.setUrl("jdbc:mysql://localhost:3306/cinehub_db?serverTimezone=UTC");

        // --- 3. XAMPP Default Credentials ---
        dataSource.setUsername("root"); // Default XAMPP MySQL username
        dataSource.setPassword("");      // Default XAMPP MySQL password (blank)
        return dataSource;
    }

    // --- 2. Entity Manager Factory (Hibernate Configuration) ---

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        // 1. Link to the database connection (the dataSource() bean above)
        emf.setDataSource(dataSource());

        // 2. Tell Hibernate where to find your @Entity classes
        emf.setPackagesToScan("com.cinehub.model");

        // 3. Set the persistence provider (Hibernate) and its adapter
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);

        // 4. Set the Hibernate properties
        Properties jpaProperties = new Properties();

        // Automatically create/update the database schema
        // Note: 'update' is good for development; 'none' is better for production.
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");

        // Print SQL statements to the console
        jpaProperties.setProperty("hibernate.show_sql", "true");

        // Format the printed SQL nicely
        jpaProperties.setProperty("hibernate.format_sql", "true");

        // *** CRITICAL CHANGE: Use the MySQL Dialect ***
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        emf.setJpaProperties(jpaProperties);
        return emf;
    }

    // --- 3. Transaction Manager (Crucial for ACID properties) ---

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        // Link the transaction manager to the configured EntityManager
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

}
