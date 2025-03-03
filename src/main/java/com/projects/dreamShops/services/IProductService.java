package com.projects.dreamShops.services;

import java.util.List;

import com.projects.dreamShops.exchange.request.ProductRequest;
import com.projects.dreamShops.exchange.response.ProductResponse;

public interface IProductService {
    ProductResponse addProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(String id);

    void deleteProduct(String id);

    ProductResponse updateProduct(ProductRequest productRequest, String productId);

    List<ProductResponse> getProductsByCategory(String category);

    List<ProductResponse> getProductsByBrand(String brand);

    List<ProductResponse> getProductsByCategoryAndBrand(String category, String brand);

    List<ProductResponse> getProductsByName(String name);

    List<ProductResponse> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);
}
