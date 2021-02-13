package it.academy.web.controller;

import it.academy.model.Product;
import it.academy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class ProductController {

    public static final Logger log =
            Logger.getLogger(ProductController.class.getName());

    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public String addProduct() {
        return "add-product";
    }

    @GetMapping("/product/{id}")
    public String getProduct(
            @PathVariable(name = "id") String id,
            Model model
    ) {
        try {
            model.addAttribute("product", productService.findProductById(id));
            return "product";
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            model.addAttribute("errorMessage", "Product not found");
            return "error";
        }
    }

    @PostMapping("/product/add")
    public String addProduct(
            @ModelAttribute Product product
    ) {
        log.info(String.format("New product: %s", product));

        String productId = productService.saveNewProduct(product);

        return String.format("redirect:/product/%s", productId);
    }
}
