package org.scaler.productservice.productservice;

import org.scaler.productservice.dtos.FakeStoreDto;
import org.scaler.productservice.exception.ProductNotFoundException;
import org.scaler.productservice.models.Category;
import org.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service("FakeProductService")
public class FakeStoreProductServiceImpl implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private String specificProductUrl = "https://fakestoreapi.com/products/{id}";
    private String getAllProductUrl = "https://fakestoreapi.com/products";
    private String addProductUrl = "https://fakestoreapi.com/products";
    @Autowired
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDto> responseEntity =  restTemplate.getForEntity(specificProductUrl, FakeStoreDto.class,id);
        if(responseEntity.getBody() == null){
            throw new ProductNotFoundException("Product not found for id:" + id);
        }

        return getProductFromFakeStoreDto(responseEntity.getBody());
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDto[]> responseEntity =  restTemplate.getForEntity(getAllProductUrl, FakeStoreDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreDto fakeStoreDto:responseEntity.getBody()){
            products.add(getProductFromFakeStoreDto(fakeStoreDto));
        }
        return products;
    }

    @Override
    public void deleteProductById() {

    }

    @Override
    public Product addProduct(Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDto> responseEntity =  restTemplate.postForEntity(addProductUrl,
                getFakeStoreDtoFromProduct(product), FakeStoreDto.class);

        return getProductFromFakeStoreDto(responseEntity.getBody());

    }

    @Override
    public void updateProductById() {

    }


    private Product getProductFromFakeStoreDto(FakeStoreDto fakeStoreDto){
        Product product = new Product();
        product.setId(fakeStoreDto.getId());
        product.setDesc(fakeStoreDto.getDescription());
        product.setPrice(fakeStoreDto.getPrice());
        product.setTitle(fakeStoreDto.getTitle());
        Category category = new Category();
        category.setName(fakeStoreDto.getCategory());
        product.setCategory(category);
        return product;

    }

    private FakeStoreDto getFakeStoreDtoFromProduct(Product product){
        FakeStoreDto fakeStoreDto = new FakeStoreDto();
        fakeStoreDto.setCategory(product.getCategory().getName());
        fakeStoreDto.setDescription(product.getDesc());
        fakeStoreDto.setTitle(product.getTitle());
        fakeStoreDto.setId(product.getId());
        fakeStoreDto.setId(product.getId());
        fakeStoreDto.setPrice(product.getPrice());
        return fakeStoreDto;

    }
}
