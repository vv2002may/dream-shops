package com.projects.dreamShops.services.product;

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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ICatgoryRepository categoryRepository;

    @Override
    public Product addProduct(ProductRequest productRequest) {

        Category category = Optional.ofNullable(categoryRepository.findByName(productRequest.getCategory()))
                .orElseGet(() -> {
                    Category newCategory = new Category(productRequest.getCategory());
                    return categoryRepository.save(newCategory);
                });
        Product newProduct = new Product(productRequest, category);
        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(ProductRequest productRequest, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found!"));
        Category category = Optional.ofNullable(categoryRepository.findByName(productRequest.getCategory()))
                .orElseGet(() -> {
                    Category newCategory = new Category(productRequest.getCategory());
                    return categoryRepository.save(newCategory);
                });
        product.setCategory(category);
        product.updateProduct(productRequest);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete, () -> {
            throw new ResourceNotFoundException("Product Not Found!");
        });
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found!"));

        return product;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategoryName(category);
        return products;
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        List<Product> products = productRepository.findByBrand(brand);
        return products;
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        List<Product> products = productRepository.findByCategoryNameAndBrand(category, brand);
        return products;
    }

    @Override
    public List<Product> getProductsByName(String name) {
        List<Product> products = productRepository.findByName(name);
        return products;
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        List<Product> products = productRepository.findByBrandAndName(brand, name);
        return products;
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

}
