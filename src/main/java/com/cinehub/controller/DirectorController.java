package com.cinehub.controller;

import com.cinehub.model.Director;
import com.cinehub.service.DirectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/directors") // Base URL
public class DirectorController {

    private final DirectorService directorService;

    // Constructor Injection
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    // --- 1. POST: Create a New Director ---
    // Endpoint: POST /directors
    @PostMapping
    public ResponseEntity<Director> createDirector(@RequestBody Director director) {
        // Validation (e.g., @Valid) should go here in a real application
        Director createdDirector = directorService.save(director);
        return new ResponseEntity<>(createdDirector, HttpStatus.CREATED);
    }

    // --- 2. GET: Retrieve All Directors ---
    // Endpoint: GET /directors
    @GetMapping
    public ResponseEntity<List<Director>> getAllDirectors() {
        List<Director> directors = directorService.findAll();
        return ResponseEntity.ok(directors);
    }

    // --- 3. GET: Retrieve Director by ID ---
    // Endpoint: GET /directors/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Director> getDirectorById(@PathVariable Long id) {
        try {
            Director director = directorService.findById(id);
            return ResponseEntity.ok(director); // HTTP 200 OK
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build(); // HTTP 404 Not Found
        }
    }

    // --- 4. PUT: Update an Existing Director ---
    // Endpoint: PUT /directors/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Director> updateDirector(
            @PathVariable Long id,
            @RequestBody Director directorDetails) {

        try {
            // NOTE: You must implement the update method in DirectorService first!
            Director updatedDirector = directorService.update(id, directorDetails);
            return ResponseEntity.ok(updatedDirector);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build(); // HTTP 404 Not Found
        }
    }

    // --- 5. DELETE: Delete Director by ID ---
    // Endpoint: DELETE /directors/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id) {
        try {
            directorService.delete(id);
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}