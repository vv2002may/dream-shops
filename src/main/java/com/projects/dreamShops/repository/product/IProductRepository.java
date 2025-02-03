package com.projects.dreamShops.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.dreamShops.model.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryNameAndBrand(String category, String brand);

    List<Product> findByName(String name);

    List<Product> findByBrandAndName(String brand, String name);

    Long countByBrandAndName(String brand, String name);
    
}
