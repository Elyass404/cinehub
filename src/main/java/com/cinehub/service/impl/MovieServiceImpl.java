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

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;
    private final CategoryRepository categoryRepository;

    // Constructor Injection for all dependencies
    public MovieServiceImpl(MovieRepository movieRepository,
                            DirectorRepository directorRepository,
                            CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Helper method to convert Entity to Response DTO
     */
    private MovieResponseDTO toDto(Movie movie) {
        MovieResponseDTO dto = new MovieResponseDTO();

        // 1. Map Core Movie Fields (These are the ones returning null)
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());

        // <--- CRITICAL: ADD THESE MISSING LINES --->
        dto.setDescription(movie.getDescription());
        dto.setSynopsis(movie.getSynopsis());
        dto.setRating(movie.getRating());
        dto.setDuration(movie.getDuration()); // Ensure type compatibility (int vs Integer)
        dto.setReleaseDate(movie.getReleaseDate());

        // 2. Map Relational IDs and Names
        // IDs (Confirmed to be working: 4 and 3 in your output)
        dto.setDirectorId(movie.getDirector().getId());
        dto.setCategoryId(movie.getCategory().getId());

        // Names (These rely on the Director/Category fields being correctly filled)
        // NOTE: Director's full name must be constructed from first and last names
        Director director = movie.getDirector();
        dto.setDirectorFullName(director.getFirstName() + " " + director.getLastName());

        // Category Name
        dto.setCategoryName(movie.getCategory().getName());

        return dto;
    }

    /**
     * Helper method to map DTO fields onto a Movie Entity,
     * including resolving the Director and Category entities from their IDs.
     */
    private Movie mapFromDto(MovieRequestDTO dto, Movie movie) {
        // Resolve Director and Category Entities from their IDs
        Director director = directorRepository.findById(dto.getDirectorId())
                .orElseThrow(() -> new NoSuchElementException("Director not found with ID: " + dto.getDirectorId()));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("Category not found with ID: " + dto.getCategoryId()));

        // Map primitive fields
        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        movie.setSynopsis(dto.getSynopsis());
        movie.setRating(dto.getRating());
        movie.setDuration(dto.getDuration() != null ? dto.getDuration() : 0); // Handle Integer vs int
        movie.setReleaseDate(dto.getReleaseDate());

        // Map relationships
        movie.setDirector(director);
        movie.setCategory(category);

        return movie;
    }


    // ------------------------------------------------------------------
    // CRUD IMPLEMENTATION
    // ------------------------------------------------------------------

    @Override
    @Transactional // Write operation
    public MovieResponseDTO save(MovieRequestDTO movieDto) {
        Movie movie = new Movie();
        movie = mapFromDto(movieDto, movie); // Use the helper to populate the entity
        Movie savedMovie = movieRepository.save(movie);
        return toDto(savedMovie);
    }

    @Override
    public List<MovieResponseDTO> findAll() {
        return movieRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MovieResponseDTO findById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Movie not found with ID: " + id));
        return toDto(movie);
    }

    @Override
    @Transactional // Write operation
    public MovieResponseDTO update(Long id, MovieRequestDTO movieDto) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Movie not found with ID: " + id));

        // Use the helper method to apply DTO fields and resolve relationships
        existingMovie = mapFromDto(movieDto, existingMovie);

        Movie updatedMovie = movieRepository.save(existingMovie);
        return toDto(updatedMovie);
    }

    @Override
    @Transactional // Write operation
    public void delete(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new NoSuchElementException("Movie not found with ID: " + id);
        }
        movieRepository.deleteById(id);
    }

    @Override
    public Optional<MovieResponseDTO> findByTitle(String title) {
        // 1. Get the Optional<Movie> from the Repository
        Optional<Movie> movieOptional = movieRepository.findByTitle(title);

        // 2. Map the Movie (if present) to a MovieResponseDTO, and return the new Optional
        return movieOptional.map(this::toDto);
    }

    @Override
    public List<MovieResponseDTO> findByReleaseYear(int year) {

        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);

        return movieRepository.findByReleaseDateBetween(startDate, endDate).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<MovieResponseDTO> findByMinRating(Double minRating) {
        if (minRating == null) {
            // Or throw IllegalArgumentException, depending on preferred error handling
            return findAll();
        }
        return movieRepository.findByRatingGreaterThanEqual(minRating).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<MovieResponseDTO> findByCategoryId(Long categoryId) {

        return movieRepository.findByCategoryId(categoryId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}