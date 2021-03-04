package it.academy.service;

import it.academy.dao.ProductDao;
import it.academy.dao.PromoDao;
import it.academy.dto.PromoAndProductDto;
import it.academy.model.Promo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Getter
@Setter
@ToString
@Service("promoService")
@PropertySource("classpath:/app.properties")
public class PromoService {

    @Value("${promo.service.name}")
    private String name;

    @Autowired
    @Qualifier("promoDaoImpl")
    private PromoDao promoDao;

    @Autowired
    private ProductDao productDao;

    static PromoService create() {
        System.out.println("Call create()");
        PromoService promoService = new PromoService();
        promoService.setName("promoService2");
        return promoService;
    }

    public void init() {
        System.out.println("Call init()");
        this.name = "promoService3";
    }

    public void destroy() {
        System.out.println("Call destroy()");
    }

    public void printPromo() {
        System.out.println("Hi, I have promo for you");
        promoDao.findAllPromo().forEach(System.out::println);
    }

    @Transactional
    public List<Promo> findAllPromo() {
        return promoDao.findAllPromo();
    }

    @Transactional
    public PromoAndProductDto findPromoAndProduct() {
        PromoAndProductDto dto = new PromoAndProductDto();
        int promoCount = promoDao.getPromoCount();
        int productCount = productDao.getProductCount();

        dto.setPromoCount(promoCount);
        dto.setProductCount(productCount);

        return dto;
    }
}
