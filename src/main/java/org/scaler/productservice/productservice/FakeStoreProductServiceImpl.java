package org.scaler.productservice.productservice;

import org.scaler.productservice.dtos.FakeStoreDto;
import org.scaler.productservice.exception.ProductNotFoundException;
import org.scaler.productservice.models.Category;
import org.scaler.productservice.models.Product;
import org.scaler.productservice.thirdparty.FakeStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service("FakeProductService")
public class FakeStoreProductServiceImpl implements ProductService{
    private FakeStoreClient fakeStoreClient;

    @Autowired
    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient){
        this.fakeStoreClient = fakeStoreClient;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return getProductFromFakeStoreDto(fakeStoreClient.getProductById(id));
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        for(FakeStoreDto fakeStoreDto: fakeStoreClient.getAllProducts()){
            products.add(getProductFromFakeStoreDto(fakeStoreDto));
        }
        return products;
    }

    @Override
    public Product deleteProductById(Long id) {
        return getProductFromFakeStoreDto(fakeStoreClient
                .deleteProductById(id));
    }

    @Override
    public Product addProduct(Product product) {
        return getProductFromFakeStoreDto(fakeStoreClient
                .addProduct(getFakeStoreDtoFromProduct(product)));
    }

    @Override
    public Product updateProductById(Product product, Long id) {
        return getProductFromFakeStoreDto(fakeStoreClient
                .updateProductById(getFakeStoreDtoFromProduct(product),id));

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
