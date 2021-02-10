package it.academy.service;

import it.academy.dao.ProductDao;
import it.academy.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    ProductDao productDao;

    public List<Product> searchProducts(String param) {
        return productDao.findAllProducts()
                .stream()
                .filter(product -> product.getProductName().contains(param)
                        || product.getDescription().contains(param))
                .collect(Collectors.toList());
    }

}
