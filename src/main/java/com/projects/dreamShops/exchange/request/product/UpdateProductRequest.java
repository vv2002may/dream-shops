package com.projects.dreamShops.exchange.request.product;

import java.math.BigDecimal;

import com.projects.dreamShops.model.Category;

import lombok.Data;

@Data
public class UpdateProductRequest {
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
}
