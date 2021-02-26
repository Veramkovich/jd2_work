package it.academy.dao;

import it.academy.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;

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

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(DaoConfiguration.class);

        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));

        ProductDao productDao = context.getBean("productDaoImpl", ProductDao.class);
        System.out.println(productDao);
    }
}
