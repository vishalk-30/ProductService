package org.scaler.productservice.thirdparty;

import org.scaler.productservice.dtos.FakeStoreDto;
import org.scaler.productservice.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import java.util.List;
@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;
    private String specificProductUrl = "https://fakestoreapi.com/products/{id}";
    private String getAllProductUrl = "https://fakestoreapi.com/products";
    private String addProductUrl = "https://fakestoreapi.com/products";
    @Autowired
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreDto getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDto> responseEntity =  restTemplate.getForEntity(specificProductUrl, FakeStoreDto.class,id);
        if(responseEntity.getBody() == null){
            throw new ProductNotFoundException("Product not found for id:" + id);
        }

        return responseEntity.getBody();
    }

    public List<FakeStoreDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDto[]> responseEntity =  restTemplate.getForEntity(getAllProductUrl, FakeStoreDto[].class);

        return List.of(responseEntity.getBody());
    }


    public FakeStoreDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //restTemplate.delete(specificProductUrl,id); this one for delete and object is not returned
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreDto.class);

        return restTemplate.execute(specificProductUrl,
                HttpMethod.DELETE, requestCallback, responseExtractor,id).getBody();

    }

    public FakeStoreDto addProduct(FakeStoreDto fakeStoreDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreDto> responseEntity =  restTemplate.postForEntity(addProductUrl,
                fakeStoreDto, FakeStoreDto.class);

        return responseEntity.getBody();
    }


    public void updateProductById() {

    }



}
