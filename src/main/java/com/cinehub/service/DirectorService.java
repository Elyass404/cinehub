package com.cinehub.service;

import com.cinehub.model.Director;

import java.util.List;

public interface DirectorService {

    Director save(Director category);
    List<Director> findAll();
    Director findById(Long id);
    void delete (Long id);

    //This is a custom method to find the director by name
    Director findByName(String name);
}
