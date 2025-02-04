package com.projects.dreamShops.services.product;

import java.util.List;

import com.projects.dreamShops.exchange.request.product.AddProductRequest;
import com.projects.dreamShops.exchange.request.product.UpdateProductRequest;
import com.projects.dreamShops.exchange.response.product.GetProductResponse;
import com.projects.dreamShops.model.Product;

public interface IProductService {
    GetProductResponse addProduct(AddProductRequest productRequest);

    List<GetProductResponse> getAllProducts();
    GetProductResponse getProductById(Long id);

    void deleteProduct(Long id);
    Product updateProduct(UpdateProductRequest productRequest, Long productId);
    
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);
}
