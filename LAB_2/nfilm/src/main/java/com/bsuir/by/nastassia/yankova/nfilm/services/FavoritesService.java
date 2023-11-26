package com.bsuir.by.nastassia.yankova.nfilm.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.bsuir.by.nastassia.yankova.nfilm.dao.DatabaseDAO;
import com.bsuir.by.nastassia.yankova.nfilm.dao.DatabaseDAOFactory;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.DatabaseDAOException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;

/**
 * Service class for managing favorites in the application.
 */
public class FavoritesService {

    private static FavoritesService instance = new FavoritesService();
    private static DatabaseDAO databaseDAO = null;
    private static Logger logger = LogManager.getLogger(FavoritesService.class.getName());

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private FavoritesService() {
    }

    /**
     * Returns the singleton instance of the FavoritesService.
     *
     * @return the singleton instance of the FavoritesService
     * @throws ServiceException if an error occurs while initializing the DatabaseDAO
     */
    public static FavoritesService getInstance() throws ServiceException {
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
     * Adds a film to the favorites for a user.
     *
     * @param idFilm the ID of the film to be added to favorites
     * @param idUser the ID of the user
     * @throws ServiceException if an error occurs while adding the film to favorites
     */
    public void addFilmToFavorites(Integer idFilm, Integer idUser) throws ServiceException {
        try {
            databaseDAO.addFilmToFavorites(idFilm, idUser);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Deletes a film from the favorites for a user.
     *
     * @param idFilm the ID of the film to be deleted from favorites
     * @param idUser the ID of the user
     * @throws ServiceException if an error occurs while deleting the film from favorites
     */
    public void deleteFilmFromFavorites(Integer idFilm, Integer idUser) throws ServiceException {
        try {
            databaseDAO.deleteFilmFromFavorites(idFilm, idUser);
        } catch (DatabaseDAOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }
}