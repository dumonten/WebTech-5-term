package com.bsuir.by.nastassia.yankova.nfilm.commands.implementation;

import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.controller.PageURLMapper;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The ToRegistrationPageCommand class represents a command that is executed to navigate to the registration page.
 * It returns the URL of the registration page.
 */
public class ToRegistrationPageCommand implements Command {

    /**
     * Executes the command logic to navigate to the registration page.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the registration page
     * @throws CommandException if any error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return PageURLMapper.REGISTER;
    }
}