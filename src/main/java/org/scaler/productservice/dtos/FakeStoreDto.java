package org.scaler.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.scaler.productservice.models.Category;
@Getter
@Setter
public class FakeStoreDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
}
