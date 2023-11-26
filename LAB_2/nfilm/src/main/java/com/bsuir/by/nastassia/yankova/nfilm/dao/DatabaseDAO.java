package com.bsuir.by.nastassia.yankova.nfilm.dao;

import java.util.ArrayList;
import com.bsuir.by.nastassia.yankova.nfilm.units.Review;
import com.bsuir.by.nastassia.yankova.nfilm.units.Film;
import com.bsuir.by.nastassia.yankova.nfilm.units.User;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.DatabaseDAOException;

/**
 * The DatabaseDAO interface defines the contract for interacting with the database in the application.
 * It provides methods for performing various database operations related to films, users, reviews, and favorites.
 */
public interface DatabaseDAO {

    /**
     * Authenticates a user with the provided login and password.
     *
     * @param login the user login
     * @param password the user password
     * @return the authenticated user
     * @throws DatabaseDAOException if an error occurs during the authorization process
     */
    User authorization(String login, String password) throws DatabaseDAOException;

    /**
     * Registers a new user with the provided login and password.
     *
     * @param login the user login
     * @param password the user password
     * @return the registered user
     * @throws DatabaseDAOException if an error occurs during the registration process
     */
    User registration(String login, String password) throws DatabaseDAOException;

    /**
     * Adds a new film to the database with the specified details.
     *
     * @param title the film title
     * @param description the film description
     * @param genre the film genre
     * @param duration the film duration
     * @param director the film director
     * @param releaseYear the film release year
     * @param language the film language
     * @param country the film country
     * @param price the film price
     * @param imageURL the URL of the film image
     * @throws DatabaseDAOException if an error occurs while adding the film
     */
    void addFilm(String title, String description, String genre, String duration, String director,
                 Integer releaseYear, String language, String country, Double price, String imageURL) throws DatabaseDAOException;

    /**
     * Updates a film in the database with the specified ID and details.
     *
     * @param idFilm the ID of the film to update
     * @param title the updated film title
     * @param description the updated film description
     * @param genre the updated film genre
     * @param duration the updated film duration
     * @param director the updated film director
     * @param releaseYear the updated film release year
     * @param language the updated film language
     * @param country the updated film country
     * @param price the updated film price
     * @param imageURL the updated URL of the film image
     * @throws DatabaseDAOException if an error occurs while updating the film
     */
    void updateFilmById(Integer idFilm, String title, String description, String genre, String duration, String director,
                        Integer releaseYear, String language, String country, Double price, String imageURL) throws DatabaseDAOException;

    /**
     * Deletes a film from the database with the specified ID.
     *
     * @param idFilm the ID of the film to delete
     * @throws DatabaseDAOException if an error occurs while deleting the film
     */
    void deleteFilmById(Integer idFilm) throws DatabaseDAOException;

    /**
     * Retrieves the total number of users in the database.
     *
     * @return the total number of users
     * @throws DatabaseDAOException if an error occurs while retrieving the user amount
     */
    Integer getUserAmount() throws DatabaseDAOException;

    /**
     * Retrieves a list of users with pagination support.
     *
     * @param pageNumberInUserList the page number in the user list
     * @return a list of users
     * @throws DatabaseDAOException if an error occurs while retrieving the user list
     */
    ArrayList<User> getUserList(Integer pageNumberInUserList) throws DatabaseDAOException;

    /**
     * Retrieves a user with the specified ID.
     *
     * @param idUser the ID of the user to retrieve
     * @return the user with the specified ID
     * @throws DatabaseDAOException if an error occurs while retrieving the user
     */
    User getUserById(Integer idUser) throws DatabaseDAOException;

    /**
     * Deletes the user with the specified login.
     *
     * @param login the login of the user to delete
     * @throws DatabaseDAOException if an error occurs while deleting the user
     */
    void deleteUserByLogin(String login) throws DatabaseDAOException;

    /**
     * Updates the sale rate of a user with the specified ID.
     *
     * @param idUser the ID of the user to update the sale rate
     * @param saleRate the new sale rate
     * @throws DatabaseDAOException if an error occurs while updating the sale rate
     */
    void updateUserSaleRateById(Integer idUser, Double saleRate) throws DatabaseDAOException;
    
    /**
     * Retrieves the total number of films in the database.
     *
     * @return the total number of films
     * @throws DatabaseDAOException if an error occurs while retrieving the film amount
     */
    Integer getFilmAmount() throws DatabaseDAOException;

    /**
     * Retrieves a list of films with pagination support.
     *
     * @param pageNumberInFilmList the page number in the film list
     * @return a list of films for the specified page number
     * @throws DatabaseDAOException if an error occurs while retrieving the film list
     */
    ArrayList<Film> getFilmList(Integer pageNumberInFilmList) throws DatabaseDAOException;

    /**
     * Retrieves a film with the specified ID.
     *
     * @param idFilm the ID of the film to retrieve
     * @return the film with the specified ID
     * @throws DatabaseDAOException if an error occurs while retrieving the film
     */
    Film getFilmById(Integer idFilm) throws DatabaseDAOException;

    /**
     * Retrieves the status of a film for a user.
     *
     * @param idFilm the ID of the film
     * @param idUser the ID of the user
     * @return the status of the film for the user
     * @throws DatabaseDAOException if an error occurs while retrieving the film status
     */
    Integer getFilmStatus(Integer idFilm, Integer idUser) throws DatabaseDAOException;

    /**
     * Retrieves a list of films in the cart for a user.
     *
     * @param idUser the ID of the user
     * @return a list of films in the cart for the user
     * @throws DatabaseDAOException if an error occurs while retrieving the films in the cart
     */
    ArrayList<Film> getFilmsInCartByUserId(Integer idUser) throws DatabaseDAOException;

    /**
     * Adds a film to the cart of a user.
     *
     * @param idFilm the ID of the film to add to the cart
     * @param idUser the ID of the user to add the film to their cart
     * @throws DatabaseDAOException if an error occurs while adding the film to the cart
     */
    void addFilmToCart(Integer idFilm, Integer idUser) throws DatabaseDAOException;

    /**
     * Removes a film from the cart of a user.
     *
     * @param idFilm the ID of the film to remove from the cart
     * @param idUser the ID of the user to remove the film from their cart
     * @throws DatabaseDAOException if an error occurs while removing the film from the cart
     */
    void deleteFilmFromCart(Integer idFilm, Integer idUser) throws DatabaseDAOException;

    /**
     * Purchases the films from the cart of a user.
     *
     * @param idUser the ID of the user
     * @throws DatabaseDAOException if an error occurs while purchasing the films from the cart
     */
    void purchaseFilmsFromCart(Integer idUser) throws DatabaseDAOException;

    /**
     * Retrieves the favorite films list for a user.
     *
     * @param idUser the ID of the user
     * @return a list of favorite films for the specified user
     * @throws DatabaseDAOException if an error occurs while retrieving the favorite films list
     */
    ArrayList<Film> getFavoritesList(Integer idUser) throws DatabaseDAOException;

    /**
     * Adds a film to the favorites list of a user.
     *
     * @param idFilm the ID of the film to add to the favorites list
     * @param idUser the ID of the user to add the film to their favorites list
     * @throws DatabaseDAOException if an error occurs while adding the film to the favorites list
     */
    void addFilmToFavorites(Integer idFilm, Integer idUser) throws DatabaseDAOException;

    /**
     * Removes a film from the favorites list of a user.
     *
     * @param idFilm the ID of the film to remove from the favorites list
     * @param idUser the ID of the user to remove the film from their favorites list
     * @throws DatabaseDAOException if an error occurs while removing the film from the favorites list
     */
    void deleteFilmFromFavorites(Integer idFilm, Integer idUser) throws DatabaseDAOException;

    /**
     * Checks if a film is in the favorites list of a user.
     *
     * @param idFilm the ID of the film to check
     * @param idUser the ID of the user
     * @return 1 if the film is in the favorites list, 0 otherwise
     * @throws DatabaseDAOException if an error occurs while checking if the film is in the favorites list
     */
    Integer isFilmInFavorites(Integer idFilm, Integer idUser) throws DatabaseDAOException;

    /**
     * Retrieves the reviews for a film with the specified ID.
     *
     * @param idFilm the ID of the film to retrieve reviews for
     * @return a list of reviews for the specified film
     * @throws DatabaseDAOException if an error occurs while retrieving the reviews
     */
    ArrayList<Review> getReviewsByFilmId(Integer idFilm) throws DatabaseDAOException;

    /**
     * Adds a review for a film.
     *
     * @param idFilm the ID of the film
     * @param username the username of the user who wrote the review
     * @param rating the rating of the film in the review
     * @param description the description of the review
     * @throws DatabaseDAOException if an error occurs while adding the review
     */
    void addReview(Integer idFilm, String username, Integer rating, String description) throws DatabaseDAOException;

    /**
     * Deletes a review with the specified ID.
     *
     * @param idReview the ID of the review to delete
     * @throws DatabaseDAOException if an error occurs while deleting the review
     */
    void deleteReviewById(Integer idReview) throws DatabaseDAOException;

    /**
     * Updates a review with the specified ID.
     *
     * @param idReview the ID of the review to update
     * @param rating the updated rating for the review
     * @param description the updated description for the review
     * @throws DatabaseDAOException if an error occurs while updating the review
     */
    void updateReviewById(Integer idReview, Integer rating, String description) throws DatabaseDAOException;
}