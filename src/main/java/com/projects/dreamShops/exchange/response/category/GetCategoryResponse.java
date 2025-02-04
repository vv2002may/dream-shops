package com.projects.dreamShops.exchange.response.category;

import com.projects.dreamShops.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetCategoryResponse {
    private Long id;
    private String name;
    // private List<GetProductResponse> products;

    public GetCategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        // this.products = category.getProducts().stream().map(GetProductResponse::new).toList();
    }
 
}
