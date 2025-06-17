package com.projects.dreamShops.services.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.dreamShops.exception.ResourceAlreadyExistException;
import com.projects.dreamShops.exception.ResourceNotFoundException;
import com.projects.dreamShops.exchange.request.ProductRequest;
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

    public Category getCategory(ProductRequest productRequest) {
        Category category = Optional.ofNullable(categoryRepository.findByName(productRequest.getCategory()))
                .orElseGet(() -> {
                    Category newCategory = new Category(productRequest.getCategory());
                    return categoryRepository.save(newCategory);
                });

        return category;
    }

    @Override
    public Product addProduct(ProductRequest productRequest) {

        Product product = productRepository.findByNameAndBrand(productRequest.getName(), productRequest.getBrand());

        if (product == null) {
            Category category = getCategory(productRequest);
            Product newProduct = new Product(productRequest, category);
            return productRepository.save(newProduct);
        } else {
            // product.setInventory(product.getInventory() + productRequest.getInventory());
            // return productRepository.save(product);

            throw new ResourceAlreadyExistException("Product already exists!");
        }

    }

    @Override
    public Product updateProduct(ProductRequest productRequest, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found!"));
        if (productRequest.getCategory() != null) {
            Category category = getCategory(productRequest);
            product.setCategory(category);
        }
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

    // @Override
    // public List<Product> getProductsByBrandAndName(String brand, String name) {
    // List<Product> products = productRepository.findByBrandAndName(brand, name);
    // return products;
    // }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

}
