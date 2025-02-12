package com.projects.dreamShops.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projects.dreamShops.exception.ResourceAlreadyExistException;
import com.projects.dreamShops.exception.ResourceNotFoundException;
import com.projects.dreamShops.exchange.request.CategoryRequest;
import com.projects.dreamShops.exchange.response.CategoryResponse;
import com.projects.dreamShops.model.Category;
import com.projects.dreamShops.repository.ICatgoryRepository;
import com.projects.dreamShops.services.ICategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final ICatgoryRepository catgoryRepository;

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = catgoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found!"));

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
    public Category addCategory(CategoryRequest categoryRequest) {

        // Category newCategory = new Category(categoryRequest.getName());
        // return catgoryRepository.save(newCategory);

        return Optional.of(categoryRequest)
                .filter(c -> !catgoryRepository.existsByName(c.getName()))
                .map(request -> new Category(request.getName()))
                .map(catgoryRepository::save)
                .orElseThrow(
                        () -> new ResourceAlreadyExistException(categoryRequest.getName() + "Category Already Exist!"));
    }

    @Override
    public Category updateCategory(CategoryRequest categoryRequest, Long id) {
        Category category = catgoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found!"));
        category.setName(categoryRequest.getName());
        return catgoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        catgoryRepository.findById(id).ifPresentOrElse(catgoryRepository::delete,
                () -> new ResourceNotFoundException("Category Not Found!"));
    }

}
