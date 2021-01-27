package it.academy.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void read() {
        //Given
        cleanInsert("ProductTest.xml");

        //When
        Session session = factory.openSession();
        List<Product> products = session
                .createQuery("from Product", Product.class)
                .list();

        //Then
        assertNotNull(products);
        assertEquals(3, products.size());

        List<ProductPrice> prices = products.stream()
                .filter(product -> "2c931081773acfd101773acfd4180002"
                        .equals(product.getProductId()))
                .map(Product::getProductPrices)
                .findFirst()
                .orElse(null);
        assertNotNull(prices);
        assertEquals(1, prices.size());

        deleteDataset();
        session.close();
    }

    @Test
    public void delete() {
        //Given
        cleanInsert("ProductTest.xml");
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Product> products = session
                .createQuery("from Product", Product.class)
                .list();
        List<ProductPrice> prices = session
                .createQuery("from ProductPrice", ProductPrice.class)
                .list();

        //When
        products.forEach(session::delete);
        prices.forEach(session::delete);
        transaction.commit();

        //Then
        assertEquals(
                0,
                session.createQuery("from Product").list().size()
        );
        session.close();
    }


}