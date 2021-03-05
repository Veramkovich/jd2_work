package it.academy.dao;

import it.academy.model.Promo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes = DaoConfigurationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class PromoDaoImplTest {

    @Autowired
    @Qualifier("promoDaoImpl")
    PromoDao promoDao;

    @Test
    public void findAllPromo() {
    }

    @Test
    public void getPromoCount() {
        //Given
        Promo promo1 = new Promo();
        promo1.setDescription("Promo1");
        Promo promo2 = new Promo();
        promo2.setDescription("Promo2");
        promoDao.create(promo1);
        promoDao.create(promo2);

        //When
        int promoCount = promoDao.getPromoCount();

        //Then
        assertTrue(promoCount >= 2);

    }
}