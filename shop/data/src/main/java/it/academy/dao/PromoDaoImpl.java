package it.academy.dao;

import it.academy.model.Promo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("promoDaoImpl")
public class PromoDaoImpl implements PromoDao {

    private Boolean isTest;

    public PromoDaoImpl(@Value("true") Boolean isTest) {
        System.out.println("isTest=" + isTest);
        this.isTest = isTest;
    }

    @Override
    public List<Promo> findAllPromo() {
        Promo promo1 = new Promo();
        promo1.setDescription("Promo1");
        Promo promo2 = new Promo();
        promo2.setDescription("Promo2");

        return List.of(promo1, promo2);
    }
}
