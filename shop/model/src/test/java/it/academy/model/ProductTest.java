package it.academy.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class ProductTest extends BaseTest {

    @Test
    public void create() {
        //Given
        Product product = new Product();
        product.setProductName("Apple iPhone");
        product.setDescription("iPhone 12 512GB");

        ProductPrice productPrice1 = new ProductPrice();
        productPrice1.setProduct(product);
        productPrice1.setPriceValue(BigDecimal.valueOf(5990.99));
        productPrice1.setCurrency(Currency.BYN);

        ProductPrice productPrice2 = new ProductPrice();
        productPrice2.setProduct(product);
        productPrice2.setPriceValue(BigDecimal.valueOf(2000.22));
        productPrice2.setCurrency(Currency.EUR);

        List<ProductPrice> prices = new ArrayList<>(2);
        prices.add(productPrice1);
        prices.add(productPrice2);
        product.setProductPrices(prices);

        //When
        Session session = factory.openSession();
        Transaction tx = null;
        Serializable id;
        try {
            tx = session.beginTransaction();
            //do some work
            id = session.save(product);
            session.save(productPrice1);
            session.save(productPrice2);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }

        //Then
        assertNotNull(id);
    }


}