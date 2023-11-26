package com.bsuir.by.nastassia.yankova.nfilm.services;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bsuir.by.nastassia.yankova.nfilm.dao.DatabaseDAO;
import com.bsuir.by.nastassia.yankova.nfilm.dao.DatabaseDAOFactory;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.DatabaseDAOException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;
import com.bsuir.by.nastassia.yankova.nfilm.units.Film;
import com.bsuir.by.nastassia.yankova.nfilm.units.Review;

/**
 * The FilmService class provides methods for interacting with film-related data and functionality.
 */
public class FilmService {
    private static FilmService instance = new FilmService();
    private static DatabaseDAO databaseDAO = null;
    private static Logger logger = LogManager.getLogger(FilmService.class.getName());

    /**
     * Retrieves the singleton instance of FilmService.
     *
     * @return the FilmService instance
     * @throws ServiceException if an error occurs while initializing the DatabaseDAO
     */
    public static FilmService getInstance() throws ServiceException {
        if (databaseDAO == null) {
            try {
                databaseDAO = DatabaseDAOFactory.getInstance().getDao(DatabaseDAOFactory.DaoType.MySQL);
            } catch (DatabaseDAOException e) {
                logger.error(e.getMessage());
                throw new ServiceException(e.getMessage());
            }
        }
        return instance;
    }

    /**
     * Retrieves the amount of films.
     *
     * @return the amount of films
     * @throws ServiceException if an error occurs while retrieving the film amount
     */
    public Integer getFilmAmount() throws ServiceException {
        try {
            return databaseDAO.getFilmAmount();
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Retrieves a list of films based on the given page number.
     *
     * @param pageNumberInFilmList the page number in the film list
     * @return an ArrayList of Film objects
     * @throws ServiceException if an error occurs while retrieving the film list
     */
    public ArrayList<Film> getFilmList(Integer pageNumberInFilmList) throws ServiceException {
        try {
            ArrayList<Film> filmList = databaseDAO.getFilmList(pageNumberInFilmList);
            for (Film film : filmList) {
                Double rating = 0.0;
                ArrayList<Review> reviewList = databaseDAO.getReviewsByFilmId(film.getId());
                if (reviewList.size() > 0) {
                    for (Review review : reviewList) {
                        rating += review.getRating();
                    }
                    rating /= reviewList.size();
                    film.setRating(rating);
                }
            }
            return filmList;
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Retrieves a film by its ID.
     *
     * @param idFilm the ID of the film
     * @return the Film object
     * @throws ServiceException if an error occurs while retrieving the film
     */
    public Film getFilmById(Integer idFilm) throws ServiceException {
        try {
            return databaseDAO.getFilmById(idFilm);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Retrieves the status of a film for a specific user.
     *
     * @param idFilm the ID of the film
     * @param idUser the ID of the user
     * @return the status of the film
     * @throws ServiceException if an error occurs while retrieving the film status
     */
    public Integer getFilmStatus(Integer idFilm, Integer idUser) throws ServiceException {
        try {
            return databaseDAO.getFilmStatus(idFilm, idUser);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Retrieves a list of films in the cart for a specific user.
     *
     * @param idUser the ID of the user
     * @return an ArrayList of Film objects in the cart
     * @throws ServiceException if an error occurs while retrieving the films in the cart
     */
    public ArrayList<Film> getFilmsInCartByUserId(Integer idUser) throws ServiceException {
        try {
            return databaseDAO.getFilmsInCartByUserId(idUser);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Updates a film by its ID.
     *
     * @param idFilm      the ID of the film
     * @param title       the new title of the film
     * @param description the new description of the film
     * @param genre       the new genre of the film
     * @param duration    the new duration of the film
     * @param director    the new director of the film
     * @param releaseYear the new release year of the film
     * @param language    the new language of the film
     * @param country     the new country of the film
     * @param price       the new price of the film
     * @param imageURL    the new image URL of the film
     * @throws ServiceException if an error occurs while updating the film
     */
    public void updateFilmById(Integer idFilm, String title, String description, String genre, String duration, String director,
                               Integer releaseYear, String language, String country, Double price, String imageURL) throws ServiceException {
        try {
            databaseDAO.updateFilmById(idFilm, title, description, genre, duration, director, releaseYear, language, country, price, imageURL);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Adds a new film to the database.
     *
     * @param title       the title of the film
     * @param description the description of the film
     * @param genre       the genre of the film
     * @param duration    the duration of the film
     * @param director    the director of the film
     * @param releaseYear the release year of the film
     * @param language    the language of the film
     * @param country     the country of the film
     * @param price       the price of the film
     * @param imageURL    the image URL of the film
     * @throws ServiceException if an error occurs while adding the film to the database
     */
    public void addFilm(String title, String description, String genre, String duration, String director,
                        Integer releaseYear, String language, String country, Double price, String imageURL)
                throws ServiceException {
        try {
            databaseDAO.addFilm(title, description, genre, duration, director, releaseYear, language, country, price, imageURL);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Deletes a film from the database by its ID.
     *
     * @param idFilm the ID of the film to be deleted
     * @throws ServiceException if an error occurs while deleting the film from the database
     */
    public void deleteFilmById(Integer idFilm) throws ServiceException {
        try {
            databaseDAO.deleteFilmById(idFilm);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Checks if a film is in the favorites list of a user.
     *
     * @param idFilm the ID of the film
     * @param idUser the ID of the user
     * @return 1 if the film is in the favorites list, 0 otherwise
     * @throws ServiceException if an error occurs while checking the film in the favorites list
     */
    public Integer isFilmInFavorites(Integer idFilm, Integer idUser) throws ServiceException {
        try {
            return databaseDAO.isFilmInFavorites(idFilm, idUser);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Retrieves the favorites list of a user.
     *
     * @param idUser the ID of the user
     * @return an ArrayList of Film objects in the favorites list
     * @throws ServiceException if an error occurs while retrieving the favorites list
     */
    public ArrayList<Film> getFavoritesList(Integer idUser) throws ServiceException {
        try {
            return databaseDAO.getFavoritesList(idUser);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }
}
