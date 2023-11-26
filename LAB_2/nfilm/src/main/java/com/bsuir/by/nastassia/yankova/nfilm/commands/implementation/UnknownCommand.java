package com.bsuir.by.nastassia.yankova.nfilm.commands.implementation;

import java.util.ResourceBundle;
import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.controller.LanguageResourceBundleProvider;
import com.bsuir.by.nastassia.yankova.nfilm.controller.PageURLMapper;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The UnknownCommand class represents a command that is executed when an unknown command name is provided.
 * It displays an error message to the user indicating that the command is unknown.
 */
public class UnknownCommand implements Command {

    /**
     * Executes the unknown command logic.
     * Sets an error message attribute in the request with a localized error message.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the error page
     * @throws CommandException if any error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());

        // Set the error message attribute with the localized unknown command error message
        request.setAttribute("errorMessage", bundle.getString("unknownCommandError"));

        // Return the URL of the error page
        return PageURLMapper.ERROR;
    }
}