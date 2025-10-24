package com.cinehub.repository;

import com.cinehub.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director,Long> {

    //now rah it has multiple methods to communicate and manipulate the database because it extends from the JpaRepository,
    //even if it is empty, but i can add some custom methods if the jpa doesnt offer them,

    //find director by first name or last name
    Optional<Director> findByFirstNameAndLastName(String firstName, String lastName);
}
