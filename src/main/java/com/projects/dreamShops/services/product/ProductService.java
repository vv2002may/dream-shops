package com.projects.dreamShops.services.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.dreamShops.exception.product.ProductNotFoundException;
import com.projects.dreamShops.exchange.request.product.AddProductRequest;
import com.projects.dreamShops.exchange.request.product.UpdateProductRequest;
import com.projects.dreamShops.exchange.response.product.GetProductResponse;
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
    public GetProductResponse addProduct(AddProductRequest productRequest) {

        Category category = Optional.ofNullable(categoryRepository.findByName(productRequest.getCategory()))
                .orElseGet(() -> {
                    Category newCategory = new Category(productRequest.getName());
                    return categoryRepository.save(newCategory);
                });
        Product newProduct = new Product(productRequest, category);
        productRepository.save(newProduct);
        GetProductResponse getProductResponse = new GetProductResponse(newProduct);
        return getProductResponse;
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
    public List<GetProductResponse> getAllProducts() {
        List<Product> products= productRepository.findAll();
        return products.stream().map(GetProductResponse::new).toList();
    }

    @Override
    public GetProductResponse getProductById(Long id) {
        Product product= productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));

        return new GetProductResponse(product);
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
