package com.projects.dreamShops.exchange.request.product;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AddProductRequest {
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private String category;
}
