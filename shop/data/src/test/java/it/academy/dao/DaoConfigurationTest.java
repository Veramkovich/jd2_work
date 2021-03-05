package it.academy.dao;

import com.mysql.cj.jdbc.Driver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@PropertySource(value = "classpath:datasource-test.properties")
@Configuration
@Profile("test")
@ComponentScan("it.academy.dao")
@EnableTransactionManagement
public class DaoConfigurationTest {

    @Autowired
    Environment env;

    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(env.getProperty("datasource-test.url"));
        hikariConfig.setDriverClassName(Driver.class.getName());
        hikariConfig.setUsername(env.getProperty("datasource-test.username"));
        hikariConfig.setPassword(env.getProperty("datasource-test.password"));
        hikariConfig.setMaximumPoolSize(100);

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        /*dataSource.setUrl(env.getProperty("datasource.url"));
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUsername(env.getProperty("datasource.username"));
        dataSource.setPassword(env.getProperty("datasource.password"));
        dataSource.setInitialSize(20);
        dataSource.setMaxTotal(30);*/
        return dataSource;
    }

}