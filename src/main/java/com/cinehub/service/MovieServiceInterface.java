package com.cinehub.service;

import com.cinehub.dto.MovieRequestDTO;
import com.cinehub.dto.MovieResponseDTO;
import com.cinehub.model.Movie;

import java.util.List;

public interface MovieServiceInterface {
    MovieResponseDTO save(MovieRequestDTO movieDto);
    List<MovieResponseDTO> findAll();
    MovieResponseDTO findById(Long id);
    void delete(Long id);

    //Here again a custom method to find the movies by name
    MovieResponseDTO findByTitle(String title);
}
