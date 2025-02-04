package com.projects.dreamShops.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.dreamShops.exception.category.CategoryExistException;
import com.projects.dreamShops.exception.category.CategoryNotFoundException;
import com.projects.dreamShops.exchange.request.category.AddCategoryRequest;
import com.projects.dreamShops.exchange.request.category.UpdateCategoryRequest;
import com.projects.dreamShops.exchange.response.ApiResponse;
import com.projects.dreamShops.services.category.ICategoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
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
        try{
            return ResponseEntity.ok(new ApiResponse("Categories fetched successfully", categoryService.getAllCategories()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to fetch categories", e.getMessage()));
        }
    }
    
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody AddCategoryRequest name) {
        try {
            return ResponseEntity.ok(new ApiResponse("Category added successfully", categoryService.addCategory(name)));
        }
        catch(CategoryExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse(e.getMessage(), null));
        }
         catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to add category", e.getMessage()));
        }
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new ApiResponse("Category fetched successfully", categoryService.getCategoryById(id)));
        }
        catch(CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } 
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to fetch category", e.getMessage()));
        }
    }

    @GetMapping("/category/name/{name}")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(new ApiResponse("Category fetched successfully", categoryService.getCategoryByName(name)));
        }
        catch(CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } 
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to fetch category", e.getMessage()));
        }
    }
    @DeleteMapping("/category/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Category deleted successfully", null));
        }

        catch(CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } 
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to delete category", e.getMessage()));
        }
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@RequestParam UpdateCategoryRequest name, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(new ApiResponse("Category updated successfully", categoryService.updateCategory(name, id)));
        }
        catch(CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } 
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to update category", e.getMessage()));
        }
    }

}

