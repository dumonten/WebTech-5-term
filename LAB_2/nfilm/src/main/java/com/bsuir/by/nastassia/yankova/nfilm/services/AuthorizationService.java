package com.bsuir.by.nastassia.yankova.nfilm.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.bsuir.by.nastassia.yankova.nfilm.dao.DatabaseDAO;
import com.bsuir.by.nastassia.yankova.nfilm.dao.DatabaseDAOFactory;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.DatabaseDAOException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;
import com.bsuir.by.nastassia.yankova.nfilm.units.User;

/**
 * Service class for managing user authorization in the application.
 */
public class AuthorizationService {

    private static AuthorizationService instance = new AuthorizationService();
    private static DatabaseDAO databaseDAO = null;
    private static Logger logger = LogManager.getLogger(AuthorizationService.class.getName());

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private AuthorizationService() {
    }

    /**
     * Returns the singleton instance of the AuthorizationService.
     *
     * @return the singleton instance of the AuthorizationService
     * @throws ServiceException if an error occurs while initializing the DatabaseDAO
     */
    public static AuthorizationService getInstance() throws ServiceException {
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
     * Registers a new user with the provided login and password.
     *
     * @param login    the user's login
     * @param password the user's password
     * @return the User object representing the newly registered user
     * @throws ServiceException if an error occurs while registering the user
     */
    public User registration(String login, String password) throws ServiceException {
        try {
            return databaseDAO.registration(login, password);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Authenticates a user with the provided login and password.
     *
     * @param login    the user's login
     * @param password the user's password
     * @return the User object representing the authenticated user
     * @throws ServiceException if an error occurs while authenticating the user
     */
    public User authorization(String login, String password) throws ServiceException {
        try {
            return databaseDAO.authorization(login, password);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }
}