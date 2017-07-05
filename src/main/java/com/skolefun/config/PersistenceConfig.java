package com.skolefun.config;

import com.google.appengine.api.utils.SystemProperty;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.skolefun", entityManagerFactoryRef = "configureEntityManagerFactory", transactionManagerRef = "annotationDrivenTransactionManager")
public class PersistenceConfig implements TransactionManagementConfigurer {

    @Bean
    public DataSource configureDataSource() {
        boolean localhost = SystemProperty.environment.value() != SystemProperty.Environment.Value.Production;
        final String urlLocal = "jdbc:mysql://localhost/skoleapp?serverTimezone=UTC";
        final String urlGoogle = "jdbc:google:mysql://sunlit-apricot-171716:europe-west1:skoleapp/skoleapp?useSSL=false";
        final String driver = localhost ? "com.mysql.jdbc.Driver": "com.mysql.jdbc.GoogleDriver";
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(localhost ? urlLocal : urlGoogle);
        dataSource.setUsername("skoleapp");
        dataSource.setPassword("skoleapp");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean configureEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(configureDataSource());
        entityManagerFactoryBean.setPackagesToScan("com.skolefun.model");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
//        jpaProperties.put(Environment.HBM2DDL_AUTO, "validate");
        jpaProperties.put(Environment.HBM2DDL_AUTO, "create");
        jpaProperties.put(Environment.SHOW_SQL, false);
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new JpaTransactionManager();
    }

}
