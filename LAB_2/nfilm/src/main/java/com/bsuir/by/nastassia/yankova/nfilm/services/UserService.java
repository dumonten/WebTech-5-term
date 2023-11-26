package com.bsuir.by.nastassia.yankova.nfilm.services;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bsuir.by.nastassia.yankova.nfilm.dao.DatabaseDAO;
import com.bsuir.by.nastassia.yankova.nfilm.dao.DatabaseDAOFactory;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.DatabaseDAOException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;
import com.bsuir.by.nastassia.yankova.nfilm.units.User;

/**
 * The UserService class provides methods for interacting with user data in the application.
 * It follows the singleton pattern and delegates database operations to a DatabaseDAO instance.
 */
public class UserService {
    private static UserService instance = new UserService();
    private static DatabaseDAO databaseDAO = null;
    private static Logger logger = LogManager.getLogger(UserService.class.getName());

    /**
     * Retrieves the singleton instance of the UserService class.
     *
     * @return the singleton instance of UserService
     * @throws ServiceException if an error occurs while creating the DatabaseDAO instance
     */
    public static UserService getInstance() throws ServiceException {
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
     * Retrieves the total number of users in the system.
     *
     * @return the number of users
     * @throws ServiceException if an error occurs while accessing the database
     */
    public Integer getUserAmount() throws ServiceException {
        try {
            return databaseDAO.getUserAmount();
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Retrieves a list of users for a specific page number in the user list.
     *
     * @param pageNumberInUserList the page number in the user list
     * @return the list of users for the specified page
     * @throws ServiceException if an error occurs while accessing the database
     */
    public ArrayList<User> getUserList(Integer pageNumberInUserList) throws ServiceException {
        try {
            return databaseDAO.getUserList(pageNumberInUserList);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Updates the sale rate for a user with the specified ID.
     *
     * @param idUser   the ID of the user
     * @param saleRate the new sale rate for the user
     * @throws ServiceException if an error occurs while accessing the database
     */
    public void updateUserSaleRateById(Integer idUser, Double saleRate) throws ServiceException {
        try {
            databaseDAO.updateUserSaleRateById(idUser, saleRate);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }
}