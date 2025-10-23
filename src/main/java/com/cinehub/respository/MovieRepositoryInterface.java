package com.cinehub.respository;

import com.cinehub.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepositoryInterface extends JpaRepository<Movie,Long> {

    //now rah it has multiple methods to communicate and manipulate the database because it extends from the JpaRepository,
    //even if it is empty, but i can add some custom methods if the jpa doesnt offer them,
}
