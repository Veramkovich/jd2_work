package it.academy.rest;

import io.swagger.annotations.ApiOperation;
import it.academy.model.Product;
import it.academy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestService {

    @Autowired
    ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> readProduct(@PathVariable String id) {
        Product product = productService.findProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products")
    public List<Product> readProducts(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize
    ) {
        System.out.println("Call readProducts " + pageNum + " " + pageSize);
        return productService.findAllProducts();
    }

    @PostMapping(value = "/products", consumes = "application/json")
    @ApiOperation("create product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productService.saveNewProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }


    @PutMapping("/products/{id}")
    @ApiOperation("update product")
    public ResponseEntity<Product> updateProduct(
            @PathVariable String id,
            @RequestBody Product product) {
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(
            @PathVariable String id
    ) {
        //productService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
