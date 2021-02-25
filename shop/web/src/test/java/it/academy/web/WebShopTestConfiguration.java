package it.academy.web;

import it.academy.service.ProductService;
import it.academy.service.PromoService;
import org.springframework.context.annotation.*;

import static org.mockito.Mockito.mock;

@Configuration
@ComponentScan(basePackages = "it.academy", lazyInit = true)
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
}
