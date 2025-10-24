package com.cinehub.dto;

import java.time.LocalDate;

/**
 * DTO for sending Movie data back to the client (JSON response).
 */
public class MovieResponseDTO {

    // Movie Attributes
    private Long id;
    private String title;
    private String description;
    private String synopsis;
    private Double rating;
    private int duration;
    private LocalDate releaseDate;

    // Related Entity Details
    private Long directorId;
    private String directorFullName;
    private Long categoryId;
    private String categoryName;

    // Default Constructor
    public MovieResponseDTO() {
    }

    // Constructor used by the Service/Mapper
    public MovieResponseDTO(Long id, String title, String description, String synopsis, Double rating,
                            int duration, LocalDate releaseDate,
                            Long directorId, String directorFullName, Long categoryId, String categoryName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.synopsis = synopsis;
        this.rating = rating;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.directorId = directorId;
        this.directorFullName = directorFullName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
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
    public Long getDirectorId() { return directorId; }
    public void setDirectorId(Long directorId) { this.directorId = directorId; }
    public String getDirectorFullName() { return directorFullName; }
    public void setDirectorFullName(String directorFullName) { this.directorFullName = directorFullName; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}