package it.academy.dao;

import it.academy.model.Promo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PromoDaoNewImpl implements PromoDao {
    @Override
    public List<Promo> findAllPromo() {
        return null;
    }

    @Override
    public int getPromoCount() {
        return 0;
    }

    @Override
    public void create(Promo promo1) {

    }
}
