package it.academy.service;

import it.academy.dao.PromoDao;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Setter
@ToString
@Service("promoService")
public class PromoService {

    @Value("promoServiceName")
    private String name;

    @Autowired
    @Qualifier("promoDaoImpl")
    private PromoDao promoDao;

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
}
