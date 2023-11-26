package com.bsuir.by.nastassia.yankova.nfilm.dao;

import com.bsuir.by.nastassia.yankova.nfilm.dao.implementation.MySQLDatabaseDAO;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.DatabaseDAOException;

/**
 * The DatabaseDAOFactory class represents a factory for creating database DAOs.
 * It uses a singleton pattern to ensure only one instance of the factory exists.
 */
public class DatabaseDAOFactory {

    private final static DatabaseDAOFactory instance = new DatabaseDAOFactory();
 
    /**
     * Enum representing the available database DAO types.
     * Currently only supports MySQL.
     */
    public enum DaoType {
        MySQL
    }

    /**
     * Constructs an instance of the DatabaseDAOFactory class.
     * It is private to restrict external instantiation.
     */
    private DatabaseDAOFactory() {}
    
    /**
     * Returns the singleton instance of the DatabaseDAOFactory.
     * 
     * @return the singleton instance of the DatabaseDAOFactory
     */
    public static DatabaseDAOFactory getInstance() {
        return instance;
    }

    /**
     * Returns a database DAO based on the provided DAO type.
     * Currently only supports MySQLDatabaseDAO.
     * 
     * @param type the type of DAO to retrieve
     * @return a database DAO
     * @throws DatabaseDAOException if the provided DAO type is not supported
     */
    public DatabaseDAO getDao(DaoType type) throws DatabaseDAOException {
        switch (type) {
            case MySQL:
                return MySQLDatabaseDAO.getInstance();
            default:
                throw new DatabaseDAOException("DAO not found.");
        }
    }
}