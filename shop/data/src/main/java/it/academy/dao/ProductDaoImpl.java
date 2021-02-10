package it.academy.dao;

import it.academy.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private static List<Product> products = List.of(
            Product.builder()
                    .productId("1")
                    .productName("Apple iPhone 12")
                    .description("256 GB white")
                    .build(),
            Product.builder()
                    .productId("2")
                    .productName("Nokia 3310")
                    .description("2G gray")
                    .build(),
            Product.builder()
                    .productId("3")
                    .productName("Samsung Galaxy 10")
                    .description("512 GB black")
                    .build()
    );


    @Override
    public List<Product> findAllProducts() {
        return products;
    }
}
