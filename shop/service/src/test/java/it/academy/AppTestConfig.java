package it.academy;

import it.academy.dao.*;
import it.academy.model.Promo;
import org.hibernate.SessionFactory;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;

import java.util.List;

@Configuration
@ComponentScan({"it.academy.service"})
@Profile("test")
public class AppTestConfig {

    @Bean
    @Primary
    public ProductDao productDao() {
        return Mockito.mock(ProductDaoImpl.class);
    }

    @Bean
    @Primary
    public VisitorCountDao visitorCountDao() {
        return Mockito.mock(VisitorCountDaoImpl.class);
    }

    @Bean
    @Primary
    public PromoDao promoDaoImpl() {
        Promo promo = new Promo();
        promo.setDescription("Promo1");
        final PromoDao promoDaoMock = Mockito.mock(PromoDaoImpl.class);
        Mockito
                .when(promoDaoMock.findAllPromo())
                .thenReturn(List.of(promo, promo));
        return promoDaoMock;
    }

    @Bean
    @Primary
    public SessionFactory sessionFactory() {
        return Mockito.mock(SessionFactory.class);
    }
}
