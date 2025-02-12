package com.projects.dreamShops.services.category;

import java.util.List;

import com.projects.dreamShops.exchange.request.category.AddCategoryRequest;
import com.projects.dreamShops.exchange.request.category.UpdateCategoryRequest;
import com.projects.dreamShops.exchange.response.category.CategoryResponse;
import com.projects.dreamShops.model.Category;

public interface ICategoryService {

    CategoryResponse getCategoryById(Long id);

    CategoryResponse getCategoryByName(String name);

    List<CategoryResponse> getAllCategories();

    Category addCategory(AddCategoryRequest categoryRequest);

    Category updateCategory(UpdateCategoryRequest categoryRequest, Long id);

    void deleteCategoryById(Long id);

}
