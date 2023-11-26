package com.bsuir.by.nastassia.yankova.nfilm.commands.implementation;

import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.commands.CommandHelper;
import com.bsuir.by.nastassia.yankova.nfilm.controller.LanguageResourceBundleProvider;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;
import com.bsuir.by.nastassia.yankova.nfilm.services.FilmService;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The DeleteFilmCommand class represents a command that is executed to handle the deletion of a film.
 * It retrieves the film ID from the request parameters, calls the FilmService to delete the film,
 * and then redirects the user to the admin page.
 */
public class DeleteFilmCommand implements Command {
    private static Logger logger = LogManager.getLogger(DeleteFilmCommand.class.getName());

    /**
     * Executes the logic for deleting a film.
     * It retrieves the film ID from the request parameters, calls the FilmService to delete the film,
     * and then redirects the user to the admin page.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the admin page
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        try {
            FilmService service = FilmService.getInstance();
            Integer idFilm = Integer.parseInt(request.getParameter("idFilm"));

            service.deleteFilmById(idFilm);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new CommandException(bundle.getString("deleteFilmError"));
        }
        Command command = CommandHelper.getInstance().getCommandByName("to_admin_page");
        return command.execute(request);
    }
}