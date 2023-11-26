package com.bsuir.by.nastassia.yankova.nfilm.units;

/**
 * Class representing a review for a film.
 */
public class Review {
    private Integer id;
    private Integer idFilm;
    private String username;
    private String description;
    private Integer rating;

    /**
     * Constructs a Review object with the specified properties.
     *
     * @param id          the ID of the review
     * @param idFilm      the ID of the film being reviewed
     * @param username    the username of the reviewer
     * @param description the description of the review
     * @param rating      the rating given to the film in the review
     */
    public Review(Integer id, Integer idFilm, String username, String description, Integer rating) {
        this.id = id;
        this.idFilm = idFilm;
        this.username = username;
        this.description = description;
        this.rating = rating;
    }

    /**
     * Returns the ID of the review.
     *
     * @return the ID of the review
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns the ID of the film being reviewed.
     *
     * @return the ID of the film being reviewed
     */
    public Integer getFilmId() {
        return idFilm;
    }

    /**
     * Returns the username of the reviewer.
     *
     * @return the username of the reviewer
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the description of the review.
     *
     * @return the description of the review
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the rating given to the film in the review.
     *
     * @return the rating given to the film in the review
     */
    public Integer getRating() {
        return rating;
    }
}