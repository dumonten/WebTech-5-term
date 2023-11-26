package com.bsuir.by.nastassia.yankova.nfilm.commands;

import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The Command interface represents a command that can be executed.
 * It defines a single method to execute the command and return the result.
 * Implementations of this interface should provide the concrete implementation of the execute method
 * to perform the desired functionality for the command.
 */
public interface Command {

    /**
     * Executes the command with the given HttpServletRequest object.
     *
     * @param request the HttpServletRequest object representing the request information
     * @return the result of executing the command as a String
     * @throws CommandException if an error occurs while executing the command
     */
    String execute(HttpServletRequest request) throws CommandException;
}
