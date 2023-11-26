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
 * The ToFavoritesPageCommand class represents a command that is executed to navigate to the favorites page.
 * It retrieves the user's favorite films from the FilmService and sets them as attributes in the request.
 * The favorites page displays a list of films that the user has marked as favorites.
 */
public class ToFavoritesPageCommand implements Command {
    private static Logger logger = LogManager.getLogger(ToFavoritesPageCommand.class.getName());

    /**
     * Executes the logic for navigating to the favorites page.
     * Retrieves the user's favorite films from the FilmService and sets them as attributes in the request.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the favorites page
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        try {
            FilmService service = FilmService.getInstance();
            Integer idUser = ((User) request.getSession().getAttribute("user")).getId(); 
            ArrayList<Film> filmList = service.getFavoritesList(idUser);
            request.setAttribute("filmList", filmList);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new CommandException(bundle.getString("toFavoritesPageError"));
        }
        return PageURLMapper.FAVORITES;
    }
}