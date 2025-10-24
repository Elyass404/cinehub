package com.cinehub.service;

import com.cinehub.dto.MovieRequestDTO;
import com.cinehub.dto.MovieResponseDTO;

import java.util.List;

public interface MovieService {
    MovieResponseDTO save(MovieRequestDTO movieDto);
    List<MovieResponseDTO> findAll();
    MovieResponseDTO findById(Long id);
    void delete(Long id);

    //Here again a custom method to find the movies by name
    MovieResponseDTO findByTitle(String title);
}
