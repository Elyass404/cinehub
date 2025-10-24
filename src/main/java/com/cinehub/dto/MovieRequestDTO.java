package com.cinehub.dto;

import java.time.LocalDate;

/**
 * DTO for receiving Movie data from the client (JSON payload).
 */
public class MovieRequestDTO {

    // Attributes matching the Movie Entity fields
    private String title;
    private String description;
    private String synopsis;
    private Double rating;
    private Integer duration;
    private LocalDate releaseDate;

    // Foreign Keys
    private Long directorId;
    private Long categoryId;

    // Default Constructor
    public MovieRequestDTO() {
    }

    // --- Getters and Setters ---

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
    public Long getDirectorId() { return directorId; }
    public void setDirectorId(Long directorId) { this.directorId = directorId; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}