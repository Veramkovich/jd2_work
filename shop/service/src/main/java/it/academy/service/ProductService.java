package it.academy.service;

import it.academy.dao.ProductDao;
import it.academy.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    public Product findProductById(String id) {
        return productDao.read(id);
    }

    public String saveNewProduct(Product product) {
        return productDao.save(product);
    }
}
