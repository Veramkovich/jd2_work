package it.academy.web;

import com.mysql.cj.jdbc.Driver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.academy.service.ProductService;
import it.academy.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

import static org.mockito.Mockito.mock;

@PropertySource(value = "classpath:datasource-test.properties")
@Configuration
@ComponentScan(basePackages = "it.academy.web", lazyInit = true)
@Profile("test")
public class WebShopTestConfiguration {

    @Bean
    @Primary
    PromoService promoService() {
        return mock(PromoService.class);
    }

    @Bean
    @Primary
    ProductService productService() {
        return mock(ProductService.class);
    }


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
        return dataSource;
    }

}
