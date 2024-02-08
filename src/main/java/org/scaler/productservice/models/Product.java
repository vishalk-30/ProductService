package org.scaler.productservice.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends  BaseModel{
    private String title;
    private String desc;
    private Double price;
    private Category category;
}
