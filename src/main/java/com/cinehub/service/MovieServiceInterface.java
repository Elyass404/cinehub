package com.cinehub.service;

import com.cinehub.model.Movie;

public interface MovieServiceInterface {
    Movie save(Movie movie);
    Movie findAll();
    Movie findById(Long id);
    void delete(Long id);

    //Here again a custom method to find the movies by name
    Movie findByName(String name);
}
