package com.projects.dreamShops.model;

import java.math.BigDecimal;
import java.util.List;

import com.projects.dreamShops.exchange.request.product.AddProductRequest;
import com.projects.dreamShops.exchange.request.product.UpdateProductRequest;
import com.projects.dreamShops.exchange.response.product.ProductResponse;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;// stock
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> image;

    public Product(AddProductRequest productRequest, Category category) {
        this.name = productRequest.getName();
        this.brand = productRequest.getBrand();
        this.price = productRequest.getPrice();
        this.inventory = productRequest.getInventory();
        this.description = productRequest.getDescription();
        this.category = category;
    }

    public void updateProduct(UpdateProductRequest productRequest) {
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
