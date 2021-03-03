package it.academy.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class PromoTest extends BaseTest {

    @Test
    public void create() {
        //Given
        Product product11 = createProduct(11);
        Product product22 = createProduct(22);
        Product product33 = createProduct(33);

        Promo promo1 = createPromo(1);
        Promo promo2 = createPromo(2);
        Promo promo3 = createPromo(3);

        ProductPromoMapper.map(promo1, List.of(product11));
        ProductPromoMapper.map(promo2, List.of(product11, product22, product33));
        ProductPromoMapper.map(promo3, List.of(product11, product33));

        //When
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        final Serializable save11 = session.save(product11);
        final Serializable save22 = session.save(product22);
        final Serializable save33 = session.save(product33);

        final Serializable save1 = session.save(promo1);
        final Serializable save2 = session.save(promo2);
        final Serializable save3 = session.save(promo3);

        transaction.commit();

        //Then
        assertNotNull(save1);
        assertNotNull(save2);
        assertNotNull(save3);
        assertNotNull(save11);
        assertNotNull(save22);
        assertNotNull(save33);

        Transaction transactionDelete = session.beginTransaction();
        session.delete(product11);
        session.delete(product22);
        session.delete(product33);
        session.delete(promo1);
        session.delete(promo2);
        session.delete(promo3);
        transactionDelete.commit();

        session.close();
    }

    private Product createProduct(int index) {
        Product product = new Product();
        product.setProductName("Product" + index);
        product.setDescription("Description" + index);
        return product;
    }

    private Promo createPromo(int index) {
        Promo promo = new Promo();
        promo.setDescription("Promo Description" + index);
        return promo;
    }

}
