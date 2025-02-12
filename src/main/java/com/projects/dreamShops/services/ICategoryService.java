package com.projects.dreamShops.services;

import java.util.List;

import com.projects.dreamShops.exchange.request.CategoryRequest;
import com.projects.dreamShops.exchange.response.CategoryResponse;
import com.projects.dreamShops.model.Category;

public interface ICategoryService {

    CategoryResponse getCategoryById(Long id);

    CategoryResponse getCategoryByName(String name);

    List<CategoryResponse> getAllCategories();

    Category addCategory(CategoryRequest categoryRequest);

    Category updateCategory(CategoryRequest categoryRequest, Long id);

    void deleteCategoryById(Long id);

}
