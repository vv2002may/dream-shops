package com.projects.dreamShops.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.projects.dreamShops.model.Product;

public interface IProductRepository extends MongoRepository<Product, String> {

    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryNameAndBrand(String category, String brand);

    List<Product> findByName(String name);

    List<Product> findByBrandAndName(String brand, String name);

    Long countByBrandAndName(String brand, String name);

}
