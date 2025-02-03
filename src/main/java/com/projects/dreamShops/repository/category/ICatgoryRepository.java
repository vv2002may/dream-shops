package com.projects.dreamShops.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.dreamShops.model.Category;

public interface ICatgoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    boolean existsByName(String name);

    
} 