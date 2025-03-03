package com.projects.dreamShops.exchange.response;

import java.util.List;

import com.projects.dreamShops.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryResponse {
    private String id;
    private String name;
    private List<ProductResponse> products;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.products = category.getProducts().stream().map(ProductResponse::new).toList();
    }

}
