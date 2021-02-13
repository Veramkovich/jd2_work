package it.academy.dao;

import it.academy.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public Product read(String id) {
        return products.stream()
                .filter(product -> product.getProductId().equals(id))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public String save(Product product) {
        int maxId = products.stream()
                .max((o1, o2) ->
                        Integer.valueOf(o1.getProductId()) - Integer.valueOf(o2.getProductId()))
                .map(product1 -> Integer.valueOf(product1.getProductId()))
                .get();
        final String productId = String.valueOf(++maxId);
        product.setProductId(productId);
        products = new ArrayList<>(products);
        products.add(product);
        return productId;
    }
}
