package com.cinehub.service;

import com.cinehub.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DirectorServiceInterface  {

    Director save(Director category);
    List<Director> findAll();
    Director findById(Long id);
    void delete (Long id);

    //This is a custom method to find the director by name
    Director findByName(String name);
}
