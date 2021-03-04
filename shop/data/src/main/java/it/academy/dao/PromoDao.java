package it.academy.dao;

import it.academy.model.Promo;

import java.util.List;

public interface PromoDao {

    List<Promo> findAllPromo();

    int getPromoCount();

    void create(Promo promo);
}
