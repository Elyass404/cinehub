package com.cinehub.service.impl;

import com.cinehub.dto.MovieRequestDTO;
import com.cinehub.dto.MovieResponseDTO;
import com.cinehub.model.Category;
import com.cinehub.model.Director;
import com.cinehub.model.Movie;
import com.cinehub.repository.CategoryRepository;
import com.cinehub.repository.DirectorRepository;
import com.cinehub.repository.MovieRepository;
import com.cinehub.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Concrete implementation of MovieService, handling DTO-to-Entity conversion
 * and transaction management.
 */
@Service
@Transactional(readOnly = true)
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;
    private final CategoryRepository categoryRepository;

    // Constructor Injection for all required Repositories
    public MovieServiceImpl(MovieRepository movieRepository,
                            DirectorRepository directorRepository,
                            CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional // Write operation
    public MovieResponseDTO save(MovieRequestDTO dto) {

        // --- CRITICAL DTO-TO-ENTITY MAPPING LOGIC ---

        // 1. Fetch Related Entities: Ensure Director and Category exist in the DB.
        Director director = directorRepository.findById(dto.getDirectorId())
                .orElseThrow(() -> new NoSuchElementException("Director not found with ID: " + dto.getDirectorId()));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("Category not found with ID: " + dto.getCategoryId()));

        // 2. Map DTO data to a new Movie Entity
        Movie movie = new Movie();
        // NOTE: For an update, you would fetch the existing movie here: movieRepository.findById(id).get()

        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        movie.setSynopsis(dto.getSynopsis());
        movie.setRating(dto.getRating());
        movie.setDuration(dto.getDuration());
        movie.setReleaseDate(dto.getReleaseDate());

        // 3. Set the complex relationships
        movie.setDirector(director);
        movie.setCategory(category);

        // 4. Save the fully-constructed Entity
        Movie savedMovie = movieRepository.save(movie);

        // 5. Convert the saved Entity back to a Response DTO for return
        return convertToResponseDTO(savedMovie);
    }

    @Override
    public List<MovieResponseDTO> findAll() {
        // Fetch all entities, then use streams to convert each one to a DTO
        return movieRepository.findAll().stream()
                .map(this::convertToResponseDTO) // Method reference to the mapper
                .collect(Collectors.toList());
    }

    @Override
    public MovieResponseDTO findById(Long id) {
        // Find the Entity, handle the case where it doesn't exist, and convert
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Movie not found with ID: " + id));
        return convertToResponseDTO(movie);
    }

    @Override
    public MovieResponseDTO findByTitle(String title) {
        // 1. Call the custom repository method. Use Optional for safety.
        Movie movie = movieRepository.findByTitle(title)
                // 2. Handle the "not found" scenario by throwing a standard exception.
                .orElseThrow(() -> new NoSuchElementException("Movie not found with title: " + title));

        // 3. Convert the found Entity back to a Response DTO before returning.
        return convertToResponseDTO(movie);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new NoSuchElementException("Cannot delete. Movie not found with ID: " + id);
        }
        movieRepository.deleteById(id);
    }

    // --- PRIVATE UTILITY METHOD (MAPPING) ---

    /**
     * Converts a Movie Entity into a simplified MovieResponseDTO.
     * @param movie The JPA managed Entity.
     * @return The DTO ready for JSON serialization.
     */
    private MovieResponseDTO convertToResponseDTO(Movie movie) {
        Director director = movie.getDirector();
        Category category = movie.getCategory();

        // NOTE: We rely on the LAZY loading here. The findById above triggered a load.
        // In findAll(), fetching the related data here will often cause N+1 query issue.
        // A more advanced solution would use JOIN FETCH in a custom repository method,
        // but this approach is correct for a basic service implementation.

        return new MovieResponseDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getSynopsis(),
                movie.getRating(),
                movie.getDuration(),
                movie.getReleaseDate(),

                // Director Details
                director.getId(),
                director.getFirstName() + " " + director.getLastName(),

                // Category Details
                category.getId(),
                category.getName()
        );
    }
}