package it.academy.dao;

import it.academy.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;

    private static final Logger log = Logger.getLogger(ProductDaoImpl.class.getName());

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> findAllProducts() {
        return sessionFactory
                .openSession()
                .createQuery("from Product", Product.class)
                .list();
    }

    @Override
    public Product read(String id) {
        return sessionFactory
                .openSession()
                .get(Product.class, id);
    }

    @Override
    public String save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        String id = (String) session.save(product);
        return id;
    }

    @Override
    public int getProductCount() {
        long productCount = sessionFactory.getCurrentSession()
                .createQuery("select count(p.productId) from Product p", Long.class)
                .list()
                .get(0);
        log.info("Product count=" + productCount);
        return (int) productCount;
    }

}
