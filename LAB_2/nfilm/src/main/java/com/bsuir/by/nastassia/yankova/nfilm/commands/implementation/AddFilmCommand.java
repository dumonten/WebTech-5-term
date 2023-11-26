package com.bsuir.by.nastassia.yankova.nfilm.commands.implementation;

import java.util.ResourceBundle;
import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.commands.CommandHelper;
import com.bsuir.by.nastassia.yankova.nfilm.controller.LanguageResourceBundleProvider;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;
import com.bsuir.by.nastassia.yankova.nfilm.services.FilmService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The AddFilmCommand class represents a command that is executed to add a new film.
 * It retrieves the film details (title, description, genre, duration, director, release year, language, country, price, image URL)
 * from the request parameters, calls the FilmService to add the film,
 * and then redirects the user to the admin page.
 */
public class AddFilmCommand implements Command {
    private static Logger logger = LogManager.getLogger(AddFilmCommand.class.getName());

    /**
     * Executes the logic for adding a new film.
     * It retrieves the film details (title, description, genre, duration, director, release year, language, country, price, image URL)
     * from the request parameters, calls the FilmService to add the film,
     * and then redirects the user to the admin page.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the admin page to redirect to
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        try {
            FilmService service = FilmService.getInstance();
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String genre = request.getParameter("genre");
            String duration = request.getParameter("duration");
            String director = request.getParameter("director");
            Integer releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
            String language = request.getParameter("language");
            String country = request.getParameter("country");
            Double price = Double.parseDouble(request.getParameter("price"));
            String imageUrl = request.getParameter("imageUrl");

            service.addFilm(title, description, genre, duration, director, releaseYear, language, country, price, imageUrl);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new CommandException(bundle.getString("addFilmError"));
        }
        Command command = CommandHelper.getInstance().getCommandByName("to_admin_page");
        return command.execute(request);
    }
}