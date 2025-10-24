package com.cinehub.service;

import com.cinehub.dto.MovieRequestDTO;
import com.cinehub.dto.MovieResponseDTO;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    MovieResponseDTO save(MovieRequestDTO movieDto);
    List<MovieResponseDTO> findAll();
    MovieResponseDTO findById(Long id);
    MovieResponseDTO update(Long id, MovieRequestDTO movieDto);
    void delete(Long id);

    //Here again a custom method to find the movies by name
    Optional<MovieResponseDTO> findByTitle(String title);

    //Filter Movies by Release Year
    List<MovieResponseDTO> findByReleaseYear(int year);

    // Filter Movies by Minimum Rating
    List<MovieResponseDTO> findByMinRating(Double minRating);

    // Consult all movies of a given category
    List<MovieResponseDTO> findByCategoryId(Long categoryId);

}
