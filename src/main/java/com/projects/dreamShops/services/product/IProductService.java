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
    GetProductResponse updateProduct(UpdateProductRequest productRequest, Long productId);
    
    List<GetProductResponse> getProductsByCategory(String category);
    List<GetProductResponse> getProductsByBrand(String brand);
    List<GetProductResponse> getProductsByCategoryAndBrand(String category, String brand);
    List<GetProductResponse> getProductsByName(String name);
    List<GetProductResponse> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);
}
