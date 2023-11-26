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
import jakarta.servlet.http.HttpServletRequest;

/**
 * The ToUserPageCommand class represents a command that is executed to navigate to the user page.
 * It retrieves the required data from the FilmService for displaying on the user page.
 */
public class ToUserPageCommand implements Command {

    private static Logger logger = LogManager.getLogger(ToUserPageCommand.class.getName());
    private static final Integer NUMBER_OF_FILMS_PER_PAGE = 7;

    /**
     * Executes the command logic to navigate to the user page.
     * Retrieves the film list, film amount, and pagination information from the FilmService.
     * Sets the necessary attributes in the request for displaying on the user page.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the user page
     * @throws CommandException if any error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        try {
            FilmService service = FilmService.getInstance();

            // Retrieve the total amount of films
            Integer filmAmount = service.getFilmAmount();
            request.setAttribute("filmAmount", filmAmount);

            // Set the number of films per page
            request.setAttribute("numberOfFilmsPerPage", NUMBER_OF_FILMS_PER_PAGE);

            // Retrieve the current page number from the request
            Integer currentPage = Integer.parseInt(request.getParameter("page"));
            request.setAttribute("page", currentPage);

            // Retrieve the film list for the current page
            ArrayList<Film> filmList = service.getFilmList(currentPage);
            request.setAttribute("filmList", filmList);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new CommandException(bundle.getString("toUserPageError"));
        }

        // Return the URL of the user page
        return PageURLMapper.USER;
    }
}