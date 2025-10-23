package com.cinehub.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "director")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String nationality;

    // Using LocalDate for proper date handling
    private LocalDate birthDate;

    // Using @Lob for potentially large text fields (biography)
    @Lob
    private String biography;

    // RELATIONSHIP: One Director can have Many Movies.
    // Mapped by the 'director' field in the Movie class.
    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movie> movies = new ArrayList<>(); // Initialize to avoid NullPointerException

    // Default Constructor (Required by JPA/Hibernate)
    public Director() {
    }

    // Constructor for creating a Director object (excluding ID and Movies)
    public Director(String firstName, String lastName, String nationality, LocalDate birthDate, String biography) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.biography = biography;
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    // ID should typically only be set by the persistence provider (Hibernate)
    // but included for completeness.
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}