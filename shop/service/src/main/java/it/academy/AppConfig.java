package it.academy;

import it.academy.model.Promo;
import it.academy.service.PromoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan({"it.academy.dto", "it.academy.service"})
public class AppConfig {

    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(System.out::println);

        PromoService promoService = context.getBean(PromoService.class);
        System.out.println(promoService);
    }

    @Bean
    Promo promo() {
        return new Promo();
    }

}
