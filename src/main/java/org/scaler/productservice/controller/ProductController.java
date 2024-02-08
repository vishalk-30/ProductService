package org.scaler.productservice.controller;

import org.scaler.productservice.dtos.ExceptionDto;
import org.scaler.productservice.exception.ProductNotFoundException;
import org.scaler.productservice.models.Product;
import org.scaler.productservice.productservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(@Qualifier("FakeProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @PostMapping
    public Product createProduct( @RequestBody Product product){
        return productService.addProduct(product);
    }



}
