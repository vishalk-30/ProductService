package org.scaler.productservice.controller;


import org.scaler.productservice.exception.ProductNotFoundException;
import org.scaler.productservice.models.Product;
import org.scaler.productservice.productservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
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
    @DeleteMapping("{id}")
    public Product deleteProductById(@PathVariable Long id){
        return productService.deleteProductById(id);
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @PostMapping
    public Product createProduct( @RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("{id}")
    public Product updateProductById(@PathVariable Long id, @RequestBody Product product){
        return productService.updateProductById(product,id);
    }




}
