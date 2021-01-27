package it.academy.model;

import java.util.ArrayList;
import java.util.List;

public class ProductPromoMapper {

    public static void map(Promo promo, List<Product> products) {
        promo.setProducts(products);

        products.forEach(product -> {
            if (product.getPromos() == null) {
                product.setPromos(new ArrayList<>());
            }
            product.getPromos().add(promo);
        });
    }

}
