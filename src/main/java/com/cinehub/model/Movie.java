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

    // Attributes for movie details
    private String description;

    private int durationMinutes;

    private LocalDate releaseDate;

    // RELATIONSHIP 1: Many Movies to One Director
    // This creates the foreign key column 'director_id' in the MOVIE table.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    // RELATIONSHIP 2: Many Movies to One Category
    // This creates the foreign key column 'category_id' in the MOVIE table.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // --- Constructors ---

    // Default Constructor (Required by JPA/Hibernate)
    public Movie() {
    }

    // Constructor for creating a Movie object (excluding ID)
    public Movie(String title, String description, int durationMinutes, LocalDate releaseDate, Director director, Category category) {
        this.title = title;
        this.description = description;
        this.durationMinutes = durationMinutes;
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

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public Director getDirector() { return director; }
    public void setDirector(Director director) { this.director = director; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}