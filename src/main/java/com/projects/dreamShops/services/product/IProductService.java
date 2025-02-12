package com.projects.dreamShops.services.product;

import java.util.List;

import com.projects.dreamShops.exchange.request.product.AddProductRequest;
import com.projects.dreamShops.exchange.request.product.UpdateProductRequest;
import com.projects.dreamShops.exchange.response.product.ProductResponse;

public interface IProductService {
    ProductResponse addProduct(AddProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    void deleteProduct(Long id);

    ProductResponse updateProduct(UpdateProductRequest productRequest, Long productId);

    List<ProductResponse> getProductsByCategory(String category);

    List<ProductResponse> getProductsByBrand(String brand);

    List<ProductResponse> getProductsByCategoryAndBrand(String category, String brand);

    List<ProductResponse> getProductsByName(String name);

    List<ProductResponse> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);
}
