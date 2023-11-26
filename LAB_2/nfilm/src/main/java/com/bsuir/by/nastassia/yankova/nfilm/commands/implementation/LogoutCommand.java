package com.bsuir.by.nastassia.yankova.nfilm.commands.implementation;

import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.controller.PageURLMapper;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The LogoutCommand class represents a command that is executed to handle user logout functionality.
 * It removes the "user" attribute from the session, effectively logging out the user.
 * The command returns the URL of the index page, which is typically the home page for the application.
 */
public class LogoutCommand implements Command {
    
    /**
     * Executes the logic for user logout functionality.
     * It removes the "user" attribute from the session, effectively logging out the user.
     * The command returns the URL of the index page, which is typically the home page for the application.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the index page
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.getSession().setAttribute("user", null);
        return PageURLMapper.INDEX;
    }
}