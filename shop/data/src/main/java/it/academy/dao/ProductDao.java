package it.academy.dao;

import it.academy.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAllProducts();

    Product read(String id);

    String save(Product product);
}
