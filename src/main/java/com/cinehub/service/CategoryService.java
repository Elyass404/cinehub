package com.cinehub.service;

import com.cinehub.model.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);
    List<Category> findAll();
    Category findById(Long id);
    void delete (Long id);

    //custom method to find by name
    Category findByName(String name);



}
