package com.projects.dreamShops.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.projects.dreamShops.model.Category;

public interface ICatgoryRepository extends MongoRepository<Category, String> {

    Category findByName(String name);

    boolean existsByName(String name);

}