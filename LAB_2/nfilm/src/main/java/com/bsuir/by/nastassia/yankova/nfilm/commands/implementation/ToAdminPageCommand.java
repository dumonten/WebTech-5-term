package com.bsuir.by.nastassia.yankova.nfilm.commands.implementation;

import java.util.ArrayList;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.controller.LanguageResourceBundleProvider;
import com.bsuir.by.nastassia.yankova.nfilm.controller.PageURLMapper;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;
import com.bsuir.by.nastassia.yankova.nfilm.services.FilmService;
import com.bsuir.by.nastassia.yankova.nfilm.services.UserService;
import com.bsuir.by.nastassia.yankova.nfilm.units.Film;
import com.bsuir.by.nastassia.yankova.nfilm.units.User;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The ToAdminPageCommand class represents a command that is executed to navigate to the admin page.
 * It retrieves the films and users data from the FilmService and UserService, sets them as attributes in the request,
 * and returns the URL of the admin page.
 */
public class ToAdminPageCommand implements Command {
    private static final Integer NUMBER_OF_FILMS_PER_PAGE = 7;
    private static final Integer NUMBER_OF_USERS_PER_PAGE = 10;
    private static Logger logger = LogManager.getLogger(ToAdminPageCommand.class.getName());

    /**
     * Executes the logic for navigating to the admin page.
     * Retrieves the films and users data from the FilmService and UserService, sets them as attributes in the request,
     * and returns the URL of the admin page.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the admin page
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        try {
            FilmService filmService = FilmService.getInstance();
            UserService userService = UserService.getInstance();
            
            Integer filmAmount = filmService.getFilmAmount();
            request.setAttribute("filmAmount", filmAmount);
            request.setAttribute("numberOfFilmsPerPage", NUMBER_OF_FILMS_PER_PAGE);
            
            Integer currentPageNumber = Integer.parseInt(request.getParameter("page"));
            request.setAttribute("page", currentPageNumber);            
            ArrayList<Film> filmList = filmService.getFilmList(currentPageNumber);
            request.setAttribute("filmList", filmList);
           
            Integer userAmount = userService.getUserAmount();
            request.setAttribute("userAmount", userAmount);
            request.setAttribute("numberOfUsersPerPage", NUMBER_OF_USERS_PER_PAGE);   
            Integer currentPageUser = Integer.parseInt(request.getParameter("pageUser"));
            request.setAttribute("pageUser", currentPageUser);
            ArrayList<User> userList = userService.getUserList(currentPageUser);
            request.setAttribute("userList", userList);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new CommandException(bundle.getString("toAdminPageError"));
        }
        return PageURLMapper.ADMIN;
    }
}