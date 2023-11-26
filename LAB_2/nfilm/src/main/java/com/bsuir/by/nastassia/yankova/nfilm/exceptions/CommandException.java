package com.bsuir.by.nastassia.yankova.nfilm.exceptions;

/**
 * The CommandException class represents an exception specific to command operations.
 * It extends the CustomException class and provides additional functionality to handle command-related errors.
 */
public class CommandException extends CustomException {
    private static final long serialVersionUID = 994165200002575L;

    /**
     * Constructs a new CommandException object.
     */
    public CommandException() {
        super();
    }

    /**
     * Constructs a new CommandException object with the specified error message.
     *
     * @param message the error message
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new CommandException object with the specified error message and cause.
     *
     * @param message the error message
     * @param cause the cause of the exception
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}