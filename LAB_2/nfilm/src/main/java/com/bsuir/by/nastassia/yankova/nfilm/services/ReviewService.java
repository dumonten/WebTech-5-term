package com.bsuir.by.nastassia.yankova.nfilm.services;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bsuir.by.nastassia.yankova.nfilm.units.Review;
import com.bsuir.by.nastassia.yankova.nfilm.dao.DatabaseDAO;
import com.bsuir.by.nastassia.yankova.nfilm.dao.DatabaseDAOFactory;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.DatabaseDAOException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;

/**
 * The ReviewService class provides methods for interacting with review-related data and functionality.
 */
public class ReviewService {
    private static ReviewService instance = new ReviewService();
    private static DatabaseDAO databaseDAO = null; 
    private static Logger logger = LogManager.getLogger(ReviewService.class.getName());
 
    /**
     * Retrieves the singleton instance of ReviewService.
     *
     * @return the ReviewService instance
     * @throws ServiceException if an error occurs while initializing the DatabaseDAO
     */
    public static ReviewService getInstance() throws ServiceException {
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
     * Retrieves a list of reviews for a specific film ID.
     *
     * @param idFilm the ID of the film
     * @return an ArrayList of Review objects
     * @throws ServiceException if an error occurs while retrieving the reviews
     */
    public ArrayList<Review> getReviewsByFilmId(Integer idFilm) throws ServiceException {
        try {
            return databaseDAO.getReviewsByFilmId(idFilm);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Adds a review for a film.
     *
     * @param idFilm      the ID of the film
     * @param username    the username of the reviewer
     * @param rating      the rating given by the reviewer
     * @param description the description of the review
     * @throws ServiceException if an error occurs while adding the review
     */
    public void addReview(Integer idFilm, String username, Integer rating, String description) throws ServiceException {
        try {
            databaseDAO.addReview(idFilm, username, rating, description);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Deletes a review by its ID.
     *
     * @param idReview the ID of the review
     * @throws ServiceException if an error occurs while deleting the review
     */
    public void deleteReviewById(Integer idReview) throws ServiceException {
        try {
            databaseDAO.deleteReviewById(idReview);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Updates the rating and description of a review by its ID.
     *
     * @param idReview    the ID of the review
     * @param rating      the new rating
     * @param description the new description
     * @throws ServiceException if an error occurs while updating the review
     */
    public void updateReviewById(Integer idReview, Integer rating, String description) throws ServiceException {
        try {
            databaseDAO.updateReviewById(idReview, rating, description);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }
}