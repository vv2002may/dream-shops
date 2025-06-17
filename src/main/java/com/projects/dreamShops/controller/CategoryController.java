package com.projects.dreamShops.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.dreamShops.exchange.request.CategoryRequest;
import com.projects.dreamShops.exchange.response.ApiResponse;
import com.projects.dreamShops.services.category.ICategoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {
    public final ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCategories() {
        return ResponseEntity
                .ok(new ApiResponse("Categories fetched successfully", categoryService.getAllCategories()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody CategoryRequest name) {
        return ResponseEntity.ok(new ApiResponse("Category added successfully", categoryService.addCategory(name)));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity
                .ok(new ApiResponse("Category fetched successfully", categoryService.getCategoryById(categoryId)));
    }

    @GetMapping("/category/name/{name}")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name) {
        return ResponseEntity
                .ok(new ApiResponse("Category fetched successfully", categoryService.getCategoryByName(name)));
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok(new ApiResponse("Category deleted successfully", null));
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@RequestParam CategoryRequest name,
            @PathVariable Long categoryId) {
        return ResponseEntity
                .ok(new ApiResponse("Category updated successfully",
                        categoryService.updateCategory(name, categoryId)));
    }

}
