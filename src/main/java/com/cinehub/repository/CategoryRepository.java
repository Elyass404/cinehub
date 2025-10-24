package com.cinehub.repository;

import com.cinehub.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    //now rah it has multiple methods to communicate and manipulate the database because it extends from the JpaRepository,
    //even if it is empty, but i can add some custom methods if the jpa doesnt offer them,

    Optional<Category> findByName(String name);
}
