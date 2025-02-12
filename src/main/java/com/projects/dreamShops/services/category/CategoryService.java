package com.projects.dreamShops.services.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.dreamShops.exception.category.CategoryExistException;
import com.projects.dreamShops.exception.category.CategoryNotFoundException;
import com.projects.dreamShops.exchange.request.category.AddCategoryRequest;
import com.projects.dreamShops.exchange.request.category.UpdateCategoryRequest;
import com.projects.dreamShops.exchange.response.category.CategoryResponse;
import com.projects.dreamShops.model.Category;
import com.projects.dreamShops.repository.category.ICatgoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final ICatgoryRepository catgoryRepository;

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = catgoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category Not Found!"));

        return new CategoryResponse(category);
    }

    @Override
    public CategoryResponse getCategoryByName(String name) {
        Category category = catgoryRepository.findByName(name);
        return new CategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = catgoryRepository.findAll();

        return categories.stream().map(CategoryResponse::new).toList();
    }

    @Override
    public Category addCategory(AddCategoryRequest categoryRequest) {

        // Category newCategory = new Category(categoryRequest.getName());
        // return catgoryRepository.save(newCategory);

        return Optional.of(categoryRequest)
                .filter(c -> !catgoryRepository.existsByName(c.getName()))
                .map(request -> new Category(request.getName()))
                .map(catgoryRepository::save)
                .orElseThrow(() -> new CategoryExistException(categoryRequest.getName() + "Category Already Exist!"));
    }

    @Override
    public Category updateCategory(UpdateCategoryRequest categoryRequest, Long id) {
        Category category = catgoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category Not Found!"));
        category.setName(categoryRequest.getName());
        return catgoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        catgoryRepository.findById(id).ifPresentOrElse(catgoryRepository::delete,
                () -> new CategoryNotFoundException("Category Not Found!"));
    }

}
