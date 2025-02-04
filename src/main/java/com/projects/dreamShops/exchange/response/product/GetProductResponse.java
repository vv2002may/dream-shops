package com.projects.dreamShops.exchange.response.product;

import java.math.BigDecimal;

import com.projects.dreamShops.model.Product;

import lombok.Data;

@Data
public class GetProductResponse {

    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;//stock
    private String description;

    private String category;

    public GetProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.price = product.getPrice();
        this.inventory = product.getInventory();
        this.description = product.getDescription();
        this.category = product.getCategory().getName();
    }


}
