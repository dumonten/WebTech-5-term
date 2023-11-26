package com.bsuir.by.nastassia.yankova.nfilm.exceptions;

/**
 * The CustomException class represents a custom exception that can be used as a base for other specific exceptions.
 * It extends the Exception class and provides additional functionality to handle custom errors.
 */
public class CustomException extends Exception {
    private static final long serialVersionUID = 609719897308852L;

    /**
     * Constructs a new CustomException object.
     */
    public CustomException() {
        super();
    }

    /**
     * Constructs a new CustomException object with the specified error message.
     *
     * @param message the error message
     */
    public CustomException(String message) {
        super(message);
    }

    /**
     * Constructs a new CustomException object with the specified error message and cause.
     *
     * @param message the error message
     * @param cause the cause of the exception
     */
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}