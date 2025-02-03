package com.projects.dreamShops.services.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.dreamShops.exception.product.ProductNotFoundException;
import com.projects.dreamShops.exchange.request.category.AddCategoryRequest;
import com.projects.dreamShops.exchange.request.product.AddProductRequest;
import com.projects.dreamShops.exchange.request.product.UpdateProductRequest;
import com.projects.dreamShops.model.Category;
import com.projects.dreamShops.model.Product;
import com.projects.dreamShops.repository.category.ICatgoryRepository;
import com.projects.dreamShops.repository.product.IProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ICatgoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest productRequest, AddCategoryRequest categoryRequest) {

        Category category = Optional.ofNullable(categoryRepository.findByName(categoryRequest.getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(categoryRequest.getName());
                    return categoryRepository.save(newCategory);
                });
        Product newProduct = new Product(productRequest, category);
        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(UpdateProductRequest productRequest, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));
        Category category = Optional.ofNullable(categoryRepository.findByName(productRequest.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(productRequest.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        productRequest.setCategory(category);
        product.updateProduct(productRequest);
        
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete, () -> {
            throw new ProductNotFoundException("Product Not Found!");
        });
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

}
