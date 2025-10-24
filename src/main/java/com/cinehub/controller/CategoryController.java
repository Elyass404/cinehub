package com.cinehub.controller;

import com.cinehub.model.Category;
import com.cinehub.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * REST Controller for the /categories resource.
 * Handles CRUD operations for the Category entity.
 */
@RestController
@RequestMapping("/categories") // Simple base URL, consistent with /movies
public class CategoryController {

    private final CategoryService categoryService;

    // Constructor Injection
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // --- 1. POST: Create a New Category ---
    // Endpoint: POST /categories
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        // Validation/business logic can be added here
        Category createdCategory = categoryService.save(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    // --- 2. GET: Retrieve All Categories ---
    // Endpoint: GET /categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories); // HTTP 200 OK
    }

    // --- 3. GET: Retrieve Category by ID ---
    // Endpoint: GET /categories/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        try {
            Category category = categoryService.findById(id);
            return ResponseEntity.ok(category); // HTTP 200 OK
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build(); // HTTP 404 Not Found
        }
    }

    // --- 4. DELETE: Delete Category by ID ---
    // Endpoint: DELETE /categories/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.delete(id);
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}