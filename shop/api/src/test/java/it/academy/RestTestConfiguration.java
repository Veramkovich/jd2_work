package it.academy;

import com.mysql.cj.jdbc.Driver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.academy.dao.ProductDao;
import it.academy.dao.ProductDaoImpl;
import it.academy.model.Product;
import it.academy.service.ProductService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.List;

@PropertySource(value = "classpath:datasource-test.properties")
@Configuration
@ComponentScan(basePackages = "it.academy")
@Profile("test")
public class RestTestConfiguration {

    @Autowired
    Environment env;

    @Bean
    @Primary
    public ProductService productService() {
        System.out.println("Call mock productService()");
        ProductService productService =
                Mockito.mock(ProductService.class);

        Mockito.when(productService.findAllProducts())
                .thenReturn(List.of(new Product()));
        return productService;
    }

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

    @Bean
    public ProductDao productDao() {
        return Mockito.mock(ProductDaoImpl.class);
    }

}
