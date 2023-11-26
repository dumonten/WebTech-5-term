package com.bsuir.by.nastassia.yankova.nfilm.commands.implementation;

import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.controller.PageURLMapper;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The ToAuthorizationPageCommand class represents a command that is executed to navigate to the authorization page.
 * It returns the URL of the authorization page.
 */
public class ToAuthorizationPageCommand implements Command {
    /**
     * Executes the logic for navigating to the authorization page.
     * It returns the URL of the authorization page.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the authorization page
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return PageURLMapper.AUTHENTICATE; 
    }
}