package com.cinehub.repository;

import com.cinehub.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    //now rah it has multiple methods to communicate and manipulate the database because it extends from the JpaRepository,
    //even if it is empty, but i can add some custom methods if the jpa doesnt offer them,

    //Find movie by title
    Optional<Movie> findByTitle(String title);

    //Since releaseDate is LocalDate, we filter using a range between the start and end of the year.
    List<Movie> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);

    //Filter Movies by Minimum Rating
    List<Movie> findByRatingGreaterThanEqual(Double minRating);

    //Consult all movies of a given category id
    List<Movie> findByCategoryId(Long categoryId);

    List<Movie> findMoviesByDirector_FirstName(String directorFirstName);
}
