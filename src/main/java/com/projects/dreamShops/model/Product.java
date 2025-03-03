package com.projects.dreamShops.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projects.dreamShops.exchange.request.ProductRequest;
import com.projects.dreamShops.exchange.response.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @Id
    private String id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;// stock
    private String description;

    @DBRef
    private Category category;

    @DBRef
    private List<Image> image;

    public Product(ProductRequest productRequest, Category category) {
        this.name = productRequest.getName();
        this.brand = productRequest.getBrand();
        this.price = productRequest.getPrice();
        this.inventory = productRequest.getInventory();
        this.description = productRequest.getDescription();
        this.category = category;
    }

    public void updateProduct(ProductRequest productRequest) {
        this.name = productRequest.getName() == null ? this.name : productRequest.getName();
        this.brand = productRequest.getBrand() == null ? this.brand : productRequest.getBrand();
        this.price = productRequest.getPrice() == null ? this.price : productRequest.getPrice();
        this.inventory = productRequest.getInventory() >= 0 ? this.inventory : productRequest.getInventory();
        this.description = productRequest.getDescription() == null ? this.description : productRequest.getDescription();
    }

    public static List<ProductResponse> getProductResponses(List<Product> products) {
        return products.stream().map(ProductResponse::new).toList();
    }

}
