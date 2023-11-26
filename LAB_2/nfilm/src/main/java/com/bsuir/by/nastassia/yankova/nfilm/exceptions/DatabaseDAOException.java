package com.bsuir.by.nastassia.yankova.nfilm.exceptions;

/**
 * The DatabaseDAOException class represents an exception specific to database DAO operations.
 * It extends the CustomException class and provides additional functionality to handle database-related errors.
 */
public class DatabaseDAOException extends CustomException {
    private static final long serialVersionUID = 1089425870138641197L;

    /**
     * Constructs a new DatabaseDAOException object.
     */
    public DatabaseDAOException() {
        super();
    }

    /**
     * Constructs a new DatabaseDAOException object with the specified error message.
     *
     * @param message the error message
     */
    public DatabaseDAOException(String message) {
        super(message);
    }

    /**
     * Constructs a new DatabaseDAOException object with the specified error message and cause.
     *
     * @param message the error message
     * @param cause the cause of the exception
     */
    public DatabaseDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}