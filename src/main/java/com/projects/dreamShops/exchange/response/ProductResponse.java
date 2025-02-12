package com.projects.dreamShops.exchange.response;

import java.math.BigDecimal;
import java.util.List;

import com.projects.dreamShops.model.Image;
import com.projects.dreamShops.model.Product;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;// stock
    private String description;
    private String category;
    private List<ImageResponse> images;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.price = product.getPrice();
        this.inventory = product.getInventory();
        this.description = product.getDescription();
        this.category = product.getCategory().getName();
        this.images = Image.imageResponses(product.getImage());
    }

}
