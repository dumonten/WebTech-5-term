package com.bsuir.by.nastassia.yankova.nfilm.commands.implementation;

import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.bsuir.by.nastassia.yankova.nfilm.commands.Command;
import com.bsuir.by.nastassia.yankova.nfilm.commands.CommandHelper;
import com.bsuir.by.nastassia.yankova.nfilm.controller.LanguageResourceBundleProvider;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.CommandException;
import com.bsuir.by.nastassia.yankova.nfilm.exceptions.ServiceException;
import com.bsuir.by.nastassia.yankova.nfilm.services.FavoritesService;
import com.bsuir.by.nastassia.yankova.nfilm.units.User;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The DeleteFromFavoritesCommand class represents a command that is executed to handle the removal of a film from favorites.
 * It retrieves the film ID and user ID from the request parameters, calls the FavoritesService to remove the film from the user's favorites,
 * and then redirects the user to the favorites page.
 */
public class DeleteFromFavoritesCommand implements Command {
    private static Logger logger = LogManager.getLogger(DeleteFromFavoritesCommand.class.getName());

    /**
     * Executes the logic for removing a film from favorites.
     * It retrieves the film ID and user ID from the request parameters, calls the FavoritesService to remove the film from the user's favorites,
     * and then redirects the user to the favorites page.
     *
     * @param request the HttpServletRequest object
     * @return the URL of the favorites page
     * @throws CommandException if an error occurs during command execution
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ResourceBundle bundle = LanguageResourceBundleProvider.getBundle(request.getSession());
        try {
            FavoritesService service = FavoritesService.getInstance();
            Integer idUser = ((User)request.getSession().getAttribute("user")).getId();
            Integer idFilm = Integer.parseInt(request.getParameter("idFilm"));

            service.deleteFilmFromFavorites(idFilm, idUser);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new CommandException(bundle.getString("deleteFromFavoritesError"));
        }
        Command command = CommandHelper.getInstance().getCommandByName("to_favorites_page");
        return command.execute(request);
    }
}