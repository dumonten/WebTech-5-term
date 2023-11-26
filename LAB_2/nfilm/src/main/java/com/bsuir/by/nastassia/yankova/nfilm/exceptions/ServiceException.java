package com.bsuir.by.nastassia.yankova.nfilm.exceptions;

/**
 * The ServiceException class represents an exception specific to service operations.
 * It extends the CustomException class and provides additional functionality to handle service-related errors.
 */
public class ServiceException extends CustomException {
    private static final long serialVersionUID = 953382858477957L;

    /**
     * Constructs a new ServiceException object.
     */
    public ServiceException() {
        super();
    }
    
    /**
     * Constructs a new ServiceException object with the specified error message.
     *
     * @param message the error message
     */
    public ServiceException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new ServiceException object with the specified error message and cause.
     *
     * @param message the error message
     * @param cause the cause of the exception
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}