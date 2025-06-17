package com.projects.dreamShops.services.product;

import java.util.List;

import com.projects.dreamShops.exchange.request.ProductRequest;
import com.projects.dreamShops.model.Product;

public interface IProductService {
    Product addProduct(ProductRequest productRequest);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    void deleteProduct(Long id);

    Product updateProduct(ProductRequest productRequest, Long productId);

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByName(String name);

    // List<Product> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);
}
