package com.cinehub.controller;

import com.cinehub.dto.MovieRequestDTO;
import com.cinehub.dto.MovieResponseDTO;
import com.cinehub.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * REST Controller for the /api/v1/movies resource.
 * It handles HTTP requests and utilizes the MovieService.
 */
@RestController // Combines @Controller and @ResponseBody (returns data/JSON)
@RequestMapping("/movies") // Base URL for all endpoints in this class
public class MovieController {

    private final MovieService movieService;

    // Constructor Injection (Preferred Spring method)
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // --- 1. POST: Create a New Movie ---
    // Endpoint: POST /api/v1/movies
    @PostMapping
    public ResponseEntity<MovieResponseDTO> createMovie(@RequestBody MovieRequestDTO movieDto) {

        // 1. Service Layer handles DTO-to-Entity conversion, validation, and saving.
        MovieResponseDTO createdMovie = movieService.save(movieDto);

        // 2. Return the saved DTO with HTTP 201 Created status.
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    // --- 2. GET: Retrieve All Movies ---
    // Endpoint: GET /api/v1/movies
    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAllMovies() {
        List<MovieResponseDTO> movies = movieService.findAll();
        // Return list with HTTP 200 OK status.
        return ResponseEntity.ok(movies);
    }

    // --- 3. GET: Retrieve Movie by ID ---
    // Endpoint: GET /api/v1/movies/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable Long id) {
        // We use a try-catch block here to demonstrate basic error handling
        try {
            MovieResponseDTO movie = movieService.findById(id);
            return ResponseEntity.ok(movie); // HTTP 200 OK
        } catch (NoSuchElementException e) {
            // If the Service throws a NoSuchElementException, return 404 Not Found.
            return ResponseEntity.notFound().build(); // HTTP 404 Not Found
        }
    }

    // --- 4. DELETE: Delete Movie by ID ---
    // Endpoint: DELETE /api/v1/movies/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        try {
            movieService.delete(id);
            // HTTP 204 No Content (Standard for successful deletion)
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            // Return 404 if the item to delete was not found.
            return ResponseEntity.notFound().build();
        }
    }

    // --- 5. PUT: Update an Existing Movie ---
    // Accepts DTO and returns Response DTO
    // Endpoint: PUT /movies/{id}
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> updateMovie(
            @PathVariable Long id,
            @RequestBody MovieRequestDTO movieDto) {

        try {
            MovieResponseDTO updatedMovie = movieService.update(id, movieDto);
            return ResponseEntity.ok(updatedMovie); // HTTP 200 OK
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build(); // HTTP 404 Not Found
        }
    }


}