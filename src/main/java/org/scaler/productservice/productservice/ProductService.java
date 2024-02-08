package org.scaler.productservice.productservice;

import org.scaler.productservice.exception.ProductNotFoundException;
import org.scaler.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    void deleteProductById();
    Product addProduct(Product product);
    void updateProductById();
}
