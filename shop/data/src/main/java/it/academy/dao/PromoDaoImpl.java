package it.academy.dao;

import it.academy.model.Promo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Repository("promoDaoImpl")
public class PromoDaoImpl implements PromoDao {

    private static final Logger log = Logger.getLogger(PromoDaoImpl.class.getName());

    @Autowired
    SessionFactory sessionFactory;

    public PromoDaoImpl(@Value("true") Boolean isTest) {
        System.out.println("isTest=" + isTest);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Promo> findAllPromo() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Promo", Promo.class)
                .list();
    }

    @Override
    @Transactional
    public int getPromoCount() {
        final Session currentSession = sessionFactory.getCurrentSession();
        long promoCount = currentSession
                .createQuery("select count(p.promoId) from Promo p", Long.class)
                .list()
                .get(0);
        log.info("Promo count=" + promoCount);
        return (int) promoCount;
    }

    @Override
    @Transactional
    public void create(Promo promo) {
        sessionFactory.getCurrentSession().saveOrUpdate(promo);
    }
}
