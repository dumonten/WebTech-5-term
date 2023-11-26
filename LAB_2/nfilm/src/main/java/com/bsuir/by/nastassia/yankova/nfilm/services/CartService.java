package com.bsuir.by.nastassia.yankova.nfilm.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.bsuir.by.nastassia.yankova.nfilm.dao.DatabaseDAO;
import com.bsuir.by.nastassia.yankova.nfilm.dao.DatabaseDAOFactory;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.DatabaseDAOException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;

/**
 * Service class for managing the user's cart in the application.
 */
public class CartService {
    private static CartService instance = new CartService();
    private static DatabaseDAO databaseDAO = null;
    private static Logger logger = LogManager.getLogger(CartService.class.getName());

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private CartService() {
    }

    /**
     * Returns the singleton instance of the CartService.
     *
     * @return the singleton instance of the CartService
     * @throws ServiceException if an error occurs while initializing the DatabaseDAO
     */
    public static CartService getInstance() throws ServiceException {
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
     * Adds a film to the user's cart.
     *
     * @param idFilm the ID of the film to be added
     * @param idUser the ID of the user
     * @throws ServiceException if an error occurs while adding the film to the cart
     */
    public void addFilmToCart(Integer idFilm, Integer idUser) throws ServiceException {
        try {
            databaseDAO.addFilmToCart(idFilm, idUser);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Deletes a film from the user's cart.
     *
     * @param idFilm the ID of the film to be deleted
     * @param idUser the ID of the user
     * @throws ServiceException if an error occurs while deleting the film from the cart
     */
    public void deleteFilmFromCart(Integer idFilm, Integer idUser) throws ServiceException {
        try {
            databaseDAO.deleteFilmFromCart(idFilm, idUser);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Purchases the films in the user's cart.
     *
     * @param idUser the ID of the user
     * @throws ServiceException if an error occurs while purchasing the films from the cart
     */
    public void purchaseFilmsFromCart(Integer idUser) throws ServiceException {
        try {
            databaseDAO.purchaseFilmsFromCart(idUser);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }
}