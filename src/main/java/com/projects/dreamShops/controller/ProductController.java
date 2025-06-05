package com.projects.dreamShops.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.dreamShops.exception.ResourceNotFoundException;
import com.projects.dreamShops.exchange.request.ProductRequest;
import com.projects.dreamShops.exchange.response.ApiResponse;
import com.projects.dreamShops.exchange.response.ProductResponse;
import com.projects.dreamShops.model.Product;
import com.projects.dreamShops.services.product.IProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity
                    .ok(new ApiResponse("Products fetched successfully", Product.getProductResponses(products)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to fetch products", e.getMessage()));
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            return ResponseEntity
                    .ok(new ApiResponse("Product fetched successfully", new ProductResponse(product)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to fetch product", e.getMessage()));
        }
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok(new ApiResponse("Product deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to delete product", e.getMessage()));
        }
    }

    @PostMapping("/product/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductRequest productRequest) {
        try {
            Product product = productService.addProduct(productRequest);
            return ResponseEntity
                    .ok(new ApiResponse("Product added successfully", new ProductResponse(product)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to add product", e.getMessage()));
        }
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductRequest productRequest,
            @PathVariable Long productId) {
        try {
            Product product = productService.updateProduct(productRequest, productId);
            return ResponseEntity.ok(new ApiResponse("Product updated successfully",
                    new ProductResponse(product)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to update product", e.getMessage()));
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse> getProductsByCategory(@PathVariable String category) {
        try {
            List<Product> products = productService.getProductsByCategory(category);
            return ResponseEntity.ok(
                    new ApiResponse("Products fetched successfully", Product.getProductResponses(products)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to fetch products", e.getMessage()));
        }
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<ApiResponse> getProductsByBrand(@PathVariable String brand) {
        try {
            List<Product> products = productService.getProductsByBrand(brand);
            return ResponseEntity
                    .ok(new ApiResponse("Products fetched successfully", Product.getProductResponses(products)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to fetch products", e.getMessage()));
        }
    }

    @GetMapping("/category/{category}/brand/{brand}")
    public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(@PathVariable String category,
            @PathVariable String brand) {
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
            return ResponseEntity.ok(new ApiResponse("Products fetched successfully",
                    Product.getProductResponses(products)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to fetch products", e.getMessage()));
        }
    }

    @GetMapping("/brand/{brand}/name/{name}")
    public ResponseEntity<ApiResponse> getProductsByBrandAndName(@PathVariable String brand,
            @PathVariable String name) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brand, name);
            return ResponseEntity.ok(new ApiResponse("Products fetched successfully",
                    Product.getProductResponses(products)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to fetch products", e.getMessage()));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse> getProductsByName(@PathVariable String name) {
        try {
            List<Product> products = productService.getProductsByName(name);
            return ResponseEntity
                    .ok(new ApiResponse("Products fetched successfully", Product.getProductResponses(products)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to fetch products", e.getMessage()));
        }
    }

    @GetMapping("/count/brand/{brand}/name/{name}")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@PathVariable String brand,
            @PathVariable String name) {
        try {
            return ResponseEntity.ok(new ApiResponse("Products fetched successfully",
                    productService.countProductsByBrandAndName(brand, name)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to fetch products", e.getMessage()));
        }
    }
}
