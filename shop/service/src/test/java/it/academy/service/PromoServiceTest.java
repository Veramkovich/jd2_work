package it.academy.service;

import it.academy.AppTestConfig;
import it.academy.model.Promo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(classes = {AppTestConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class PromoServiceTest {

    @Autowired
    PromoService promoService;

    @org.junit.Test
    public void findAllPromo() {
        //Given

        //When
        final List<Promo> allPromo = promoService.findAllPromo();

        //Then
        assertNotNull(allPromo);
        assertEquals(2, allPromo.size());
        assertEquals("Promo1", allPromo.get(0).getDescription());
        assertNull(allPromo.get(1).getPromoId());
    }
}