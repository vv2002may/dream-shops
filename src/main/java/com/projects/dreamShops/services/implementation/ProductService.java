package com.projects.dreamShops.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.dreamShops.exception.ResourceNotFoundException;
import com.projects.dreamShops.exchange.request.ProductRequest;
import com.projects.dreamShops.exchange.response.ProductResponse;
import com.projects.dreamShops.model.Category;
import com.projects.dreamShops.model.Product;
import com.projects.dreamShops.repository.ICatgoryRepository;
import com.projects.dreamShops.repository.IProductRepository;
import com.projects.dreamShops.services.IProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ICatgoryRepository categoryRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {

        Category category = Optional.ofNullable(categoryRepository.findByName(productRequest.getCategory()))
                .orElseGet(() -> {
                    Category newCategory = new Category(productRequest.getCategory());
                    return categoryRepository.save(newCategory);
                });
        Product newProduct = new Product(productRequest, category);
        productRepository.save(newProduct);
        ProductResponse getProductResponse = new ProductResponse(newProduct);
        return getProductResponse;
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found!"));
        Category category = Optional.ofNullable(categoryRepository.findByName(productRequest.getCategory()))
                .orElseGet(() -> {
                    Category newCategory = new Category(productRequest.getCategory());
                    return categoryRepository.save(newCategory);
                });
        product.setCategory(category);
        product.updateProduct(productRequest);

        productRepository.save(product);

        return new ProductResponse(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete, () -> {
            throw new ResourceNotFoundException("Product Not Found!");
        });
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return Product.getProductResponses(products);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found!"));

        return new ProductResponse(product);
    }

    @Override
    public List<ProductResponse> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategoryName(category);
        return Product.getProductResponses(products);
    }

    @Override
    public List<ProductResponse> getProductsByBrand(String brand) {
        List<Product> products = productRepository.findByBrand(brand);
        return Product.getProductResponses(products);
    }

    @Override
    public List<ProductResponse> getProductsByCategoryAndBrand(String category, String brand) {
        List<Product> products = productRepository.findByCategoryNameAndBrand(category, brand);
        return Product.getProductResponses(products);
    }

    @Override
    public List<ProductResponse> getProductsByName(String name) {
        List<Product> products = productRepository.findByName(name);
        return Product.getProductResponses(products);
    }

    @Override
    public List<ProductResponse> getProductsByBrandAndName(String brand, String name) {
        List<Product> products = productRepository.findByBrandAndName(brand, name);
        return Product.getProductResponses(products);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

}
