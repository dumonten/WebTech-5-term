package com.bsuir.by.nastassia.yankova.nfilm.units;

/**
 * Class representing a film in the application.
 */
public class Film {
    private Integer id;
    private String title;
    private String description;
    private String duration;
    private String genre;
    private String director;
    private Integer releaseYear;
    private String country;
    private String language;
    private Double price;
    private String imageURL;
    private Double rating;

    /**
     * Constructs a new Film object with the provided properties.
     *
     * @param id           the ID of the film
     * @param description  the description of the film
     * @param title        the title of the film
     * @param duration     the duration of the film
     * @param genre        the genre of the film
     * @param director     the director of the film
     * @param releaseYear  the release year of the film
     * @param country      the country of the film
     * @param language     the language of the film
     * @param price        the price of the film
     * @param imageURL     the URL of the film's image
     */
    public Film(Integer id, String description, String title, String duration, String genre, String director,
                Integer releaseYear, String country, String language, Double price, String imageURL) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.genre = genre;
        this.director = director;
        this.releaseYear = releaseYear;
        this.country = country;
        this.language = language;
        this.price = price;
        this.imageURL = imageURL;
        this.rating = 0.0;
    }

    /**
     * Returns the ID of the film.
     *
     * @return the ID of the film
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns the title of the film.
     *
     * @return the title of the film
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the description of the film.
     *
     * @return the description of the film
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the duration of the film.
     *
     * @return the duration of the film
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Returns the genre of the film.
     *
     * @return the genre of the film
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Returns the director of the film.
     *
     * @return the director of the film
     */
    public String getDirector() {
        return director;
    }

    /**
     * Returns the release year of the film.
     *
     * @return the release year of the film
     */
    public Integer getReleaseYear() {
        return releaseYear;
    }

    /**
     * Returns the country of the film.
     *
     * @return the country of the film
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns the language of the film.
     *
     * @return the language of the film
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Returns the price of the film.
     *
     * @return the price of the film
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Returns the URL of the film's image.
     *
     * @return the URL of the film's image
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Returns the rating of the film.
     *
     * @return the rating of the film
     */
    public Double getRating() {
        return rating;
    }

    /**
     * Sets the rating of the film.
     *
     * @param rating the rating of the film
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }
}