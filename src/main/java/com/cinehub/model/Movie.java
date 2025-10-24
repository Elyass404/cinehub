package com.cinehub.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // Core Movie Details
    private String description;
    private String synopsis;
    private Double rating;
    private int duration;
    private LocalDate releaseDate;

    // RELATIONSHIP 1: Many Movies to One Director
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    // RELATIONSHIP 2: Many Movies to One Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Default Constructor (Required by JPA/Hibernate)
    public Movie() {
    }

    // Full Constructor
    public Movie(String title, String description, String synopsis, Double rating,
                 int duration, LocalDate releaseDate, Director director, Category category) {
        this.title = title;
        this.description = description;
        this.synopsis = synopsis;
        this.rating = rating;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.director = director;
        this.category = category;
    }

    // --- Getters and Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
    public Director getDirector() { return director; }
    public void setDirector(Director director) { this.director = director; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}