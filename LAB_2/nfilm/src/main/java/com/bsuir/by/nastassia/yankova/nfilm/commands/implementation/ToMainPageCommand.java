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
import com.bsuir.by.nastassia.yankova.nfilm.units.Film;
import com.bsuir.by.nastassia.yankova.nfilm.units.User;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The ToMainPageCommand class represents a command that is executed to navigate to the main page.
 * It retrieves the required data from the FilmService for displaying on the main page.
 * The main page displays a list of films to the user.
 * If the user is logged in, it retrieves the film list and sets the necessary attributes in the request.
 * If the user is not logged in, it returns the URL of the index page.
 */
public class ToMainPageCommand implements Command {

    private static final Integer NUMBER_OF_FILMS_PER_PAGE = 5;
    private static Logger logger = LogManager.getLogger(ToMainPageCommand.class.getName());

    /**
     * Executes the logic for navigating to the main page.
     * Retrieves film data and sets the necessary attributes in the request.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the main page or index page
     * @throws CommandException if any error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        User logged = (User) request.getSession().getAttribute("user");
        if (logged != null) {
            try {
                FilmService filmService = FilmService.getInstance();
                Integer filmAmount = filmService.getFilmAmount();
                request.setAttribute("filmAmount", filmAmount);
                request.setAttribute("numberOfFilmsPerPage", NUMBER_OF_FILMS_PER_PAGE);
                request.setAttribute("page", 1);
                ArrayList<Film> filmList = filmService.getFilmList(1);
                request.setAttribute("filmList", filmList);
            } catch (ServiceException e) {
                logger.error(e.getMessage());
                throw new CommandException(bundle.getString("toMainPageError"));
            }
            return PageURLMapper.USER;
        } else {
            return PageURLMapper.INDEX;
        }
    }
}