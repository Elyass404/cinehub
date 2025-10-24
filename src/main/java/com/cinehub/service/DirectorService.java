package com.cinehub.service;

import com.cinehub.model.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorService {

    Director save(Director category);
    List<Director> findAll();
    Director findById(Long id);
    Director update(Long id, Director newDirector);
    void delete (Long id);

    //This is a custom method to find the director by name
    Optional<Director> findByFullName(String firstName, String lastName);
}
